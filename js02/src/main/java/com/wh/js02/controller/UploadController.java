package com.wh.js02.controller;

import com.alibaba.fastjson.JSON;
import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.Js03UserFile;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03UserFileService;
import com.wh.js02.util.RedisUtil;
import com.wh.js02.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * author:13
 * date:2017-09-14
 */
@Controller
@RequestMapping("/api")
public class UploadController {

    @Value("${image.file.path}")
    private String imagePath;

    @Value("${image.ip}")
    private String imageIp;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Js03UserFileService js03UserFileService;

    @PostMapping("/file/pageQuery")
    public @ResponseBody
    ResultVo pageQ(@RequestBody PageDTO pageDTO) {
        PageVo pageVo = js03UserFileService.pageQuery(pageDTO);
        ResultVo resultVo = new ResultVo();
        if (pageVo != null) {
            resultVo.setBody(pageVo);
            resultVo.success();
        } else {
            resultVo.fail("图片分页查询失败");
        }
        return resultVo;
    }

    @RequestMapping("/image/upload")
    public @ResponseBody
    ResultVo upload(MultipartFile picName, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo<>();
        Date now = new Date();
        Integer userId;
        Js03User js03User;
        String toekn = request.getHeader("token");
        try {
            js03User = JSON.parseObject((String) redisUtil.getObject(toekn), Js03User.class);
        } catch (Exception e) {
            resultVo.fail("未知用户，上传文件失败");
            return resultVo;
        }
        //获取文件名
        String fileName = picName.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "")+suffixName;
        // 指定本地文件夹存储图片
        String filePath = imagePath;

        File curPic = new File(filePath+js03User.getId()+"/"+newFileName);
        if (!curPic.getParentFile().getParentFile().exists()) {
            curPic.getParentFile().getParentFile().mkdir();
        }
        if (!curPic.getParentFile().exists()) {
            curPic.getParentFile().mkdir();
        }
        try {
            picName.transferTo(curPic);

            Js03UserFile js03UserFile = new Js03UserFile();
            // 文件原名称
            js03UserFile.setFileName(fileName);
            js03UserFile.setFileType(suffixName);
            js03UserFile.setUserId(js03User.getId());
            js03UserFile.setFileUrl(imageIp+"/"+js03User.getId()+"/"+newFileName);

            js03UserFile.setCreateBy(js03User.getUserName());
            js03UserFile.setCreateTime(now);
            js03UserFile.setUpateTime(now);
            js03UserFile.setUpdateBy(js03User.getUserName());
            js03UserFile.setStatus(1);
            int count = js03UserFileService.uploadFile(js03UserFile);
            if (count > 0) {
                resultVo.success();
            } else {
                resultVo.fail("保存文件路径失败");
            }
         } catch (Exception e) {
            e.printStackTrace();
            resultVo.fail("上传文件失败");
        }
        return resultVo;
    }

    @RequestMapping("/image/update")
    public @ResponseBody
    ResultVo updateFile(MultipartFile picName, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
        String str = request.getParameter("oldPic");
        Js03UserFile oldFile = JSON.parseObject(str, Js03UserFile.class);
        //获取文件名
        String fileName = picName.getOriginalFilename();
        //获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成新文件名
        String newFilename = UUID.randomUUID().toString().replaceAll("-","")+suffixName;

        //文件存储路径
        String imagepath = imagePath;

        //当前登录用户
        Js03User curjs03User;

        //获取该登录用户的token，通过token获取redis里的用户信息的ID，用于创建该用户文件夹
        String token = request.getHeader("token");
        try {
            curjs03User = JSON.parseObject((String )redisUtil.getObject(token),Js03User.class);
        } catch (Exception e) {
            resultVo.fail("您没登录或登录过期");
            return resultVo;
        }
        String allFilePath = imagepath+"/"+curjs03User.getId()+"/"+newFilename;

        File curPicturePath = new File(allFilePath);

        if (!curPicturePath.getParentFile().exists()) {
            curPicturePath.getParentFile().mkdir();
        }

        try {
            //将新图片上传到指定的位置
            picName.transferTo(curPicturePath);
            //上传后，修改数据库图片的信息
            Js03UserFile js03UserFile = new Js03UserFile();
            js03UserFile.setFileId(oldFile.getFileId());
            js03UserFile.setFileName(fileName);
            js03UserFile.setFileType(suffixName);
            js03UserFile.setUpdateBy(curjs03User.getUserName());
            js03UserFile.setUpateTime(new Date());
            js03UserFile.setFileUrl(imageIp+"/"+curjs03User.getId()+"/"+newFilename);

            //图片修改 todo
            int count = js03UserFileService.updatePicture(js03UserFile);
            if (count == 1) {
                resultVo.success();
            } else {
                resultVo.fail("图片修改失败了");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        //不同用户的图片存储在不同文件夹

        return resultVo;
    }

    @PostMapping("image/delete")
    public @ResponseBody
    ResultVo deleteFile(@RequestBody Js03UserFile js03UserFile){
        int count = js03UserFileService.deletePircture(js03UserFile.getFileId());
        ResultVo resultVo = new ResultVo();
        if (count == 1) {
            resultVo.success();
        } else {
            resultVo.fail("删除图片失败");
        }
        return resultVo;
    }

    @PostMapping("/image/fuzzyQuery")
    public @ResponseBody
    ResultVo fuzzyQuery(@RequestBody PageDTO pageDTO){
        ResultVo resultVo = new ResultVo();
        PageVo pageVo = js03UserFileService.fuzzyQuery(pageDTO);
        if (pageVo.getListBody() != null){
            resultVo.setBody(pageVo);
            resultVo.success();
        } else {
            resultVo.fail("模糊查询图片失败");
        }
        return resultVo;
    }

}

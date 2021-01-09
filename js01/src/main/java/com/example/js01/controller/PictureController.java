package com.example.js01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@RestController
public class PictureController {

    @Value("${upload.image.path}")
    private String uploadPath;

    @PostMapping(value = "/upload/image")
    public void addPhoto(MultipartFile[] imgFile) throws Exception{
        File file = new File(uploadPath);
        // 如果不存在目录就创建该目录
        if (!file.exists()) {
            file.mkdir();
        }
        for (MultipartFile multipartFile : imgFile) {
            if (multipartFile != null) {
                // 上传文件
                String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                String filename = UUID.randomUUID() + suffix;
                File serverFile = new File(uploadPath + filename);
                if(!serverFile.exists()) {
                    //先得到文件的上级目录，并创建上级目录，在创建文件
                    serverFile.getParentFile().mkdir();
                    try {
                        //创建文件
                        serverFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //将上传的文件写入到服务器端文件内
                multipartFile.transferTo(serverFile);
            }
        }
        return;
    }

    @GetMapping("/download/image")
    public String downloadImage(HttpServletResponse response,@RequestParam("imageId") String imageId) {
        String fileName = imageId + ".jpg";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File(uploadPath+fileName);
            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                response.setContentType("application/octet-stream");//
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);



                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }
}

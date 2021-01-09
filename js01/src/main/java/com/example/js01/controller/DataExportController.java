package com.example.js01.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.js01.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class DataExportController {
    private static final String BASE_PATH = System.getProperty("java.io.tmpdir") + "Resource" + File.separator;
    @RequestMapping(value = "/excelImport",method = RequestMethod.GET)
    public void export(HttpServletRequest request,  HttpServletResponse response) {
        ZipOutputStream out = null;
        BufferedInputStream bis =  null;
        InputStream in = null;
        String tip = UUID.randomUUID().toString() + File.separator;
        try {
            createAllWorkbooks(tip);
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "EXCEL2016.zip");
            File tempZip = new File(BASE_PATH + tip + "temp.zip");
            FileUtil.createZipFile(new File(BASE_PATH+ tip), new ZipOutputStream(tempZip));
            System.out.println("Created ZIP File");
            OutputStream os = response.getOutputStream();
            in = new FileInputStream(tempZip);
            bis = new BufferedInputStream(in);
            byte buff[] = new byte[1024];
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileUtil.deleteDir(BASE_PATH);
        }
    }


    public List<Workbook> createAllWorkbooks(String tip) {
        List<Workbook> workbooks = new ArrayList<>();
        OutputStream out = null;
        try {
            for (int i=0;i<100;i++) {
                File tempFile = new File(BASE_PATH + tip + i + ".xlsx");
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();
                out = new FileOutputStream(tempFile);
                Workbook workbook = new XSSFWorkbook();
                workbook.createSheet("summary");
                workbook.getSheetAt(0).createRow(0);
                Row row = workbook.getSheetAt(0).getRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("Hello Spring Boot.");
                workbooks.add(workbook);
                workbook.write(out);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out!= null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbooks;
    }

    @PostMapping(value = "/upload")
    public String upload(MultipartFile file) throws Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //获取行数
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            row.getCell(0);
            System.out.println(row.getCell(0).getStringCellValue());
        }

        return null;
    }
}
package com.shuyan.springbootfileupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author will
 */
@RestController
public class UploadController {
    
    @PostMapping("/upload1")
    public String upload1(@RequestPart MultipartFile file) throws IOException {
        // 上传文件输入流
        //InputStream inputStream = file.getInputStream();
        // 上传文件名
        return file.getOriginalFilename();
    }

    @PostMapping("/upload2")
    public String upload2(@RequestParam MultipartFile file) throws IOException {
        // 上传文件输入流
        //InputStream inputStream = file.getInputStream();
        // 上传文件名
        return file.getOriginalFilename();
    }

    @PostMapping("/upload3")
    public String upload3(MultipartHttpServletRequest request) {
        // 获取上传文件
        MultipartResolver resolver = new CommonsMultipartResolver(request.getServletContext());
        System.out.println(resolver.isMultipart(request));
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        System.out.println(fileMap.toString());
        MultipartFile file = fileMap.get("file");

        // 上传文件名
        return file.getOriginalFilename();
    }
}

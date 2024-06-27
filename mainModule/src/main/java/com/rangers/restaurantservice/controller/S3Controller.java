package com.rangers.restaurantservice.controller;

import com.rangers.restaurantservice.service.impl.S3ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final S3ServiceImpl s3Service;

    @GetMapping("/file-upload")
    public String fileUploadPage() {
        return "form";
    }

    @PostMapping("/file-upload")
    public String saveFiles(Model model, @RequestParam("file") MultipartFile multipartFile){
        return s3Service.upload(model, multipartFile);
    }
}

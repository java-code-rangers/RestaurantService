package com.rangers.restaurantservice.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.rangers.restaurantservice.model.User;
import com.rangers.restaurantservice.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    S3Service s3Service;

    @GetMapping("/file-upload")
    public String fileUploadPage(){
        return "form";
    }

    @PostMapping("/file-upload")
    public String saveFiles(Model model,
                            @RequestParam("description") String description,
                            @RequestParam("file") MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        User user = new User();
        user.setFilename(filename);
        user.setDescription(description);

        try {
            s3Service.uploadToS3(multipartFile.getInputStream(), filename);
        } catch (AmazonServiceException e) {
            model.addAttribute("error", "AWS Service Error");
            e.printStackTrace();
        } catch (SdkClientException e) {
            model.addAttribute("error", "SDK Client Error");
            e.printStackTrace();
        } catch (IOException e) {
            model.addAttribute("error", "Error Uploading file");
            e.printStackTrace();
        }

        model.addAttribute("message", "File Successfully Upload");
        return "form";
    }
}

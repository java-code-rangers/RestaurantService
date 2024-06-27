package com.rangers.restaurantservice.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String upload(Model model, MultipartFile multipartFile);
}

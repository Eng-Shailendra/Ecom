package com.shailendra.ecom.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

   String uploadFile(MultipartFile file);

   boolean deleteFile(String imageUrl);


}

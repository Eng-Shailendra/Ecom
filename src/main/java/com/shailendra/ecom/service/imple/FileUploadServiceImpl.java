package com.shailendra.ecom.service.imple;

import com.shailendra.ecom.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final S3Client s3Client;

    @Value("${aws.bucket.name")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file) {
       String fileNameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
       String key = UUID.randomUUID().toString()+"."+fileNameExtension;
       try {
           PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                   .bucket(bucketName).key(key).contentType(file.getContentType())
                   .build();

           PutObjectResponse response = s3Client.putObject(
                   putObjectRequest, RequestBody.fromBytes(file.getBytes())
           );

           if(response.sdkHttpResponse().isSuccessful()){
               return "https://"+bucketName+".s3.amazoneaws.com/"+key;
           }else {
               throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error occurred while uploading file");
           }
       }catch (IOException e){
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File Upload Failed");
       }
    }

    @Override
    public boolean deleteFile(String imageUrl) {
        String filename= imageUrl.substring(imageUrl.lastIndexOf("/")+1);
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
        return true;

    }
}

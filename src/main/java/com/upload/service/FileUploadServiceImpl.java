package com.upload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final String UPLOAD_DIR = "uploads/";

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Void> uploadFile(MultipartFile file) {
        try {

            // Check if the uploads directory exists, if not create it
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file to the uploads directory
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            System.out.println("File uploaded successfully: " + file.getOriginalFilename());
            return CompletableFuture.completedFuture(null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage());
        }
        // throw new UnsupportedOperationException("Unimplemented method 'uploadFile'");
    }

}

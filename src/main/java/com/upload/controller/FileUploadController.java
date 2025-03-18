package com.upload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.service.FileUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    // @PostMapping("/upload")
    // public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    //     return fileUploadService.uploadFile(file);
    // }

    // @PostMapping("/uploadMultipleFiles")
    // public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
    //     return fileUploadService.uploadMultipleFiles(files);
    // }

    // @PostMapping("/uploadMultipleFilesAsync")
    // public ResponseEntity<String> uploadMultipleFilesAsync(@RequestParam("files") List<MultipartFile> files) {
    //     return fileUploadService.uploadMultipleFilesAsync(files);
    // }

    // @PostMapping("/uploadMultipleFilesAsyncWithExecutor")
    // public ResponseEntity<String> uploadMultipleFilesAsyncWithExecutor(@RequestParam("files") List<MultipartFile> files) {
    //     return fileUploadService.uploadMultipleFilesAsyncWithExecutor(files);
    // }

    // @PostMapping("/uploadMultipleFilesAsyncWithExecutorAndFutures")
    // public ResponseEntity<String> uploadMultipleFilesAsyncWithExecutorAndFutures(@RequestParam("files") List<MultipartFile> files) {
    //     return fileUploadService.uploadMultipleFilesAsyncWithExecutorAndFutures(files);
    // }

    // @PostMapping("/uploadMultipleFilesAsyncWithExecutorAndListenableFutures")
    // public ResponseEntity<String> uploadMultipleFilesAsyncWithExecutorAndListenableFutures(@RequestParam("files") List<MultipartFile> files) {
    //     return fileUploadService.uploadMultipleFilesAsyncWithExecutorAndListenableFutures(files);
    // }

    // Multiple file upload
    @PostMapping("/uploads")
    public ResponseEntity<String> uploadFile(@RequestParam("files") List<MultipartFile> files) {
        try {
            for(MultipartFile file: files) {
                fileUploadService.uploadFile(file);
            }
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to upload files: " + e.getMessage());
        }
    }

    // Single file upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadService.uploadFile(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to upload file: " + e.getMessage());
        }
    }
    
}

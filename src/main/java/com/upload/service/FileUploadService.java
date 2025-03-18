package com.upload.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    CompletableFuture<Void> uploadFile(MultipartFile file);

}

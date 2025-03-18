package com.upload.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadServiceImplTest {

    @InjectMocks
    private FileUploadServiceImpl fileUploadServiceImpl;

    @Mock
    private MultipartFile file;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadFile_Success() throws IOException {
        when(file.getOriginalFilename()).thenReturn("test.csv");
        when(file.getBytes()).thenReturn("header1, header2\nvalue1, value2".getBytes());

        CompletableFuture<Void> result = fileUploadServiceImpl.uploadFile(file);

        assertNotNull(result);
        result.join();
        Path filePath = Paths.get("uploads/test.csv");
        assertTrue(Files.exists(filePath));

        Files.deleteIfExists(filePath);
    }
}

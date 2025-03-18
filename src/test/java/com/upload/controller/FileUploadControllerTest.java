package com.upload.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.upload.service.FileUploadService;

public class FileUploadControllerTest {

    @InjectMocks
    private FileUploadController fileUploadController;

    @Mock
    private FileUploadService fileUploadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadFiles_Success() {
        MockMultipartFile file1 = new MockMultipartFile("file", "test1.csv", "text/csv", "header1, header2\nvalue1, value2".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "test2.csv", "text/csv", "header1, header2\nvalue1, value2".getBytes());

        ResponseEntity<String> response = fileUploadController.uploadFile(List.of(file1, file2));

        assertEquals(200, response.getStatusCode().value());
        assertEquals("File uploaded successfully", response.getBody());
        verify(fileUploadService, times(2)).uploadFile(any());
    }

    @Test
    void testUploadFiles_Failure() {
        MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", "header1, header2\nvalue1, value2".getBytes());
        doThrow(new RuntimeException("Failed to upload file")).when(fileUploadService).uploadFile(any());

        ResponseEntity<String> response = fileUploadController.uploadFile(List.of(file));

        assertEquals(500, response.getStatusCode().value());
        assertTrue(response.getBody().contains("Failed to upload files"));
    }
}

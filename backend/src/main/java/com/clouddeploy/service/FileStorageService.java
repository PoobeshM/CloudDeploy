package com.clouddeploy.service;

import com.clouddeploy.util.ZipUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${extract.dir}")
    private String extractDir;

    public String saveAndExtract(MultipartFile file)
            throws IOException {

        // Validate ZIP
        if (!file.getOriginalFilename().endsWith(".zip")) {
            throw new RuntimeException(
                    "Only ZIP files are allowed"
            );
        }

        // Create upload directory
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Unique ID
        String uniqueId = UUID.randomUUID().toString();

        // Save ZIP
        String zipFileName =
                uniqueId + "_" + file.getOriginalFilename();

        Path zipFilePath =
                uploadPath.resolve(zipFileName);

        Files.copy(
                file.getInputStream(),
                zipFilePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        // Create extraction directory
        Path extractionPath =
                Paths.get(extractDir, uniqueId);

        Files.createDirectories(extractionPath);

        // Extract ZIP
        ZipUtil.unzip(
                zipFilePath.toString(),
                extractionPath.toString()
        );

        return extractionPath.toFile().getAbsolutePath();
}
}
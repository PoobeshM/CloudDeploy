package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProjectRootDetectionService {

    private static final List<String> ROOT_FILES = List.of(
            "pom.xml",
            "build.gradle",
            "package.json",
            "requirements.txt",
            "manage.py"
    );

    public String findProjectRoot(String extractedPath)
            throws IOException {

        Path startPath = Paths.get(extractedPath);

        try (Stream<Path> paths = Files.walk(startPath)) {

            for (Path path : paths.toList()) {

                String fileName =
                        path.getFileName().toString();

                if (ROOT_FILES.contains(fileName)) {

                    return path.getParent().toString();
                }
            }
        }

        // fallback
        return extractedPath;
    }
}
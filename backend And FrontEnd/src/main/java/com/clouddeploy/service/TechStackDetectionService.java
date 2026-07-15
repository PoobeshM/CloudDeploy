package com.clouddeploy.service;

import com.clouddeploy.model.ProjectType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class TechStackDetectionService {

    public ProjectType detectTechnology(String projectPath)
            throws IOException {

        Path rootPath = Paths.get(projectPath);

        boolean hasPom = false;
        boolean hasGradle = false;
        boolean hasPackageJson = false;
        boolean hasRequirements = false;
        boolean hasManagePy = false;
        boolean hasAppPy = false;

        try (Stream<Path> paths = Files.walk(rootPath)) {

            for (Path path : paths.toList()) {

                String fileName =
                        path.getFileName().toString();

                switch (fileName) {

                    case "pom.xml":
                        hasPom = true;
                        break;

                    case "build.gradle":
                        hasGradle = true;
                        break;

                    case "package.json":
                        hasPackageJson = true;
                        break;

                    case "requirements.txt":
                        hasRequirements = true;
                        break;

                    case "manage.py":
                        hasManagePy = true;
                        break;

                    case "app.py":
                        hasAppPy = true;
                        break;
                }
            }
        }

        // Detection Logic

        if (hasPom) {
            return ProjectType.SPRING_BOOT;
        }

        if (hasGradle) {
            return ProjectType.GRADLE_JAVA;
        }

        if (hasManagePy) {
            return ProjectType.DJANGO;
        }

        if (hasRequirements && hasAppPy) {
            return ProjectType.FLASK;
        }

        if (hasRequirements) {
            return ProjectType.PYTHON;
        }

        if (hasPackageJson) {

            // Later we will distinguish:
            // React vs Node.js

            return ProjectType.NODE_JS;
        }

        return ProjectType.UNKNOWN;
    }
}
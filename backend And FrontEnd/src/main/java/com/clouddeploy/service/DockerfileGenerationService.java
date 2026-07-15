package com.clouddeploy.service;

import com.clouddeploy.model.ProjectType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

@Service
public class DockerfileGenerationService {

    @Value("${dockerfile.dir}")
    private String dockerfileDir;

    public String generateDockerfile(
            ProjectType projectType,
            String projectPath
    ) throws IOException {

        System.out.println("=================================");
System.out.println("DOCKERFILE GENERATION STARTED");
System.out.println("Project Type = " + projectType);
System.out.println("Project Path = " + projectPath);
System.out.println("=================================");
        String templateFile = getTemplateFile(projectType);
        // System.out.println("Dockerfile Created At = " + dockerfilePath);
        System.out.println("Template File = " + templateFile);
        if (templateFile == null) {
            throw new RuntimeException(
                    "Unsupported project type"
            );
        }

        Path templatePath =
                Paths.get("../docker-templates", templateFile);

        String dockerContent =
                Files.readString(templatePath);

        Path dockerfilePath =
                Paths.get(projectPath, "Dockerfile");

        Files.writeString(
                dockerfilePath,
                dockerContent,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        return dockerfilePath.toString();
    }

    private String getTemplateFile(ProjectType type) {

        switch (type) {

            case SPRING_BOOT:
            case GRADLE_JAVA:
                return "Dockerfile.java";

            case NODE_JS:
            case REACT:
                return "Dockerfile.node";

            case PYTHON:
            case DJANGO:
            case FLASK:
                return "Dockerfile.python";

            default:
                return null;
        }
    }
}
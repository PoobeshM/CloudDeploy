package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class KubernetesManifestService {

    public void generateManifests(
            String projectName,
            String imageName,
            int port
    ) throws IOException {

        Path deploymentTemplate =
                Paths.get(
                        "../kubernetes/templates/deployment.yaml"
                );

        Path serviceTemplate =
                Paths.get(
                        "../kubernetes/templates/service.yaml"
                );

        String deploymentContent =
                Files.readString(deploymentTemplate);

        String serviceContent =
                Files.readString(serviceTemplate);

        deploymentContent =
                deploymentContent
                        .replace("APP_NAME", projectName)
                        .replace("IMAGE_NAME", imageName)
                        .replace("PORT", String.valueOf(port));

        serviceContent =
                serviceContent
                        .replace("APP_NAME", projectName)
                        .replace("PORT", String.valueOf(port));

        Path outputFolder =
                Paths.get(
                        "../kubernetes/generated",
                        projectName
                );
System.out.println("================================");
System.out.println("GENERATING KUBERNETES YAML");
System.out.println("PROJECT = " + projectName);
System.out.println("OUTPUT = " + outputFolder);
System.out.println("================================");
        Files.createDirectories(outputFolder);

        Files.writeString(
                outputFolder.resolve("deployment.yaml"),
                deploymentContent
        );

        Files.writeString(
                outputFolder.resolve("service.yaml"),
                serviceContent
        );
    }
}
package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.UUID;

@Service
public class DockerBuildService {

    public String buildDockerImage(String projectPath)
            throws Exception {

        // Unique image name
        String imageName =
                "clouddeploy-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8);

        ProcessBuilder processBuilder =
                new ProcessBuilder();

        processBuilder.command(
                "docker",
                "build",
                "-t",
                imageName,
                "."
        );

        // Run inside extracted project folder
        processBuilder.directory(
                new java.io.File(projectPath)
        );

        processBuilder.redirectErrorStream(true);

        Process process =
                processBuilder.start();

        StringBuilder output =
                new StringBuilder();

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                process.getInputStream()
                        )
                );

        String line;

        while ((line = reader.readLine()) != null) {

            output.append(line)
                  .append("\n");
        }

        int exitCode = process.waitFor();

        if (exitCode != 0) {

            throw new RuntimeException(
                    "Docker build failed:\n" + output
            );
        }

        return imageName;
    }
}
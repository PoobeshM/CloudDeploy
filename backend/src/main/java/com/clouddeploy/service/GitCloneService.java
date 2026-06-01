package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class GitCloneService {

    public String cloneRepository(String repositoryUrl)
            throws Exception {

        String projectId =
                UUID.randomUUID().toString();

        String clonePath =
                "cloned-repos/" + projectId;

        ProcessBuilder processBuilder =
                new ProcessBuilder(
                        "git",
                        "clone",
                        repositoryUrl,
                        clonePath
                );

        processBuilder.redirectErrorStream(true);

        Process process =
                processBuilder.start();

        int exitCode =
                process.waitFor();

        if(exitCode != 0) {

            throw new RuntimeException(
                    "Git clone failed"
            );
        }

        return new File(clonePath)
                .getAbsolutePath();
    }
}
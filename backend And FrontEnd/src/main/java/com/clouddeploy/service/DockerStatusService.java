package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class DockerStatusService {

    public boolean isContainerRunning(String containerName) {

        try {

            Process process =
                    Runtime.getRuntime().exec(
                            "docker ps --filter name="
                            + containerName
                            + " --format {{.Names}}"
                    );

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String line = reader.readLine();

            return line != null &&
                   line.equals(containerName);

        }
        catch (Exception e) {

            return false;

        }

    }
}
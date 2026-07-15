package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class KubernetesDeploymentService {


public void deploy(String projectName)
        throws Exception {

    String basePath =
            "../kubernetes/generated/"
                    + projectName;

    System.out.println("================================");
    System.out.println("KUBERNETES DEPLOYMENT");
    System.out.println("PROJECT = " + projectName);
    System.out.println("PATH = " + basePath);
    System.out.println("================================");

    execute(
            "kubectl apply -f "
                    + basePath
                    + "/deployment.yaml"
    );

    execute(
            "kubectl apply -f "
                    + basePath
                    + "/service.yaml"
    );
}

private void execute(String command)
        throws Exception {

    System.out.println("EXECUTING:");
    System.out.println(command);

    Process process =
            Runtime.getRuntime()
                    .exec(command);

    BufferedReader successReader =
            new BufferedReader(
                    new InputStreamReader(
                            process.getInputStream()
                    )
            );

    BufferedReader errorReader =
            new BufferedReader(
                    new InputStreamReader(
                            process.getErrorStream()
                    )
            );

    String line;

    while ((line = successReader.readLine()) != null) {

        System.out.println(line);

    }

    while ((line = errorReader.readLine()) != null) {

        System.out.println("ERROR: " + line);

    }

    process.waitFor();

    System.out.println(
            "EXIT CODE = "
                    + process.exitValue()
    );
}

}

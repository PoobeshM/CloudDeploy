package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

@Service
public class JenkinsTriggerService {

    private static final String JENKINS_URL =
            "http://localhost:8081";
        
    private static final String JOB_NAME =
            "clouddeploy-pipeline";

    private static final String USERNAME =
            "poobesh";

    private static final String API_TOKEN =
            "1100f7ac8f99e7e4004d83a2663278c6a6";

    
    public void triggerBuild( String projectRoot, String projectName )
        throws Exception {
        String triggerUrl =
        JENKINS_URL
                + "/job/"
                + JOB_NAME
                + "/buildWithParameters"
                + "?PROJECT_PATH="
                + URLEncoder.encode(projectRoot, "UTF-8")
                + "&PROJECT_NAME="
                + URLEncoder.encode(projectName, "UTF-8");

        System.out.println("Trigger URL = " + triggerUrl);

        HttpURLConnection connection =
                (HttpURLConnection)
                        new URL(triggerUrl)
                                .openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String auth =
                USERNAME + ":" + API_TOKEN;

        String encodedAuth =
                Base64.getEncoder()
                        .encodeToString(
                                auth.getBytes()
                        );

        connection.setRequestProperty(
                "Authorization",
                "Basic " + encodedAuth
        );

        int responseCode =
                connection.getResponseCode();

        System.out.println(
                "Jenkins Response Code = "
                        + responseCode
        );

        if (responseCode != 200
                && responseCode != 201) {

            throw new RuntimeException(
                    "Failed to trigger Jenkins build. Response Code = "
                            + responseCode
            );
        }
    }
}
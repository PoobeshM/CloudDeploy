package com.clouddeploy.service;

import org.springframework.stereotype.Service;

@Service
public class JenkinsStatusService {

    public String getBuildStatus() {

        return "RUNNING";

    }
}
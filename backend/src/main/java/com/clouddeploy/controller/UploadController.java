package com.clouddeploy.controller;

import com.clouddeploy.service.DockerfileGenerationService;
import com.clouddeploy.service.FileStorageService;
import com.clouddeploy.service.GitCloneService;
import com.clouddeploy.service.JenkinsStatusService;
import com.clouddeploy.service.JenkinsTriggerService;
import com.clouddeploy.service.ProjectRootDetectionService;
import com.clouddeploy.service.TechStackDetectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ProjectRootDetectionService projectRootDetectionService;

    @Autowired
    private TechStackDetectionService detectionService;

    @Autowired
    private DockerfileGenerationService dockerfileGenerationService;

    @Autowired
    private JenkinsTriggerService jenkinsTriggerService;

    @Autowired
    private JenkinsStatusService jenkinsStatusService;

    @Autowired
    private GitCloneService gitCloneService;

    
    @PostMapping("/upload")
    public String uploadProject(
            @RequestParam("file") MultipartFile file,
            Model model
    ) {

        try {

            // STEP 1 - Upload and Extract ZIP
            String extractedPath =
                    fileStorageService.saveAndExtract(file);
                    model.addAttribute(
                "zipUploaded",
                true
                );

                model.addAttribute(
                        "projectExtracted",
                        true
                );

            // STEP 2 - Find Actual Project Root
            String projectRoot =
                    projectRootDetectionService
                            .findProjectRoot(extractedPath);
                String projectName =
        new java.io.File(projectRoot)
                .getName()
                .toLowerCase()
                .replace(" ", "-");
            // STEP 3 - Detect Technology
            var detectedType =
                    detectionService
                            .detectTechnology(projectRoot);
                            model.addAttribute(
                                "technologyDetected",
                                true
                                 );

            // STEP 4 - Generate Dockerfile
            String dockerfilePath =
                    dockerfileGenerationService
                            .generateDockerfile(
                                    detectedType,
                                    projectRoot
                            );
                        model.addAttribute(
                                "dockerfileGenerated",
                                true    
                                );

            // STEP 5 - Trigger Jenkins
            System.out.println("PROJECT ROOT = " + projectRoot);
            jenkinsTriggerService.triggerBuild( projectRoot, projectName );
            model.addAttribute(
                "jenkinsTriggered",
                true
                );
            String buildStatus = jenkinsStatusService.getBuildStatus();
                        model.addAttribute(
                                "buildStatus",
                                buildStatus
                        );
            // UI Response
            model.addAttribute(
                    "message",
                    "Deployment submitted to Jenkins successfully"
            );

            model.addAttribute(
                    "projectRoot",
                    projectRoot
            );

            model.addAttribute(
                    "technology",
                    detectedType
            );

            model.addAttribute(
                    "dockerfilePath",
                    dockerfilePath
            );

        } catch (Exception e) {

                model.addAttribute(
                        "deploymentError",
                        e.getMessage()
                );

                model.addAttribute(
                        "message",
                        "Deployment Failed"
                );

                model.addAttribute(
                        "buildStatus",
                        "FAILED"
                );

                e.printStackTrace();
                }

                
        return "result-zip";
    }
    @PostMapping("/github/deploy")
public String deployFromGitHub(

        @RequestParam("repositoryUrl")
        String repositoryUrl,

        Model model
) {

    try {

        // Clone GitHub Repository
        String clonedProjectPath =
                gitCloneService
                        .cloneRepository(
                                repositoryUrl
                        );
                        model.addAttribute(
                        "repositoryCloned",
                        true
                        );
        // Detect Project Root
        String projectRoot =
                projectRootDetectionService
                        .findProjectRoot(
                                clonedProjectPath
                        );
        String projectName = new java.io.File(projectRoot)
                .getName()
                .toLowerCase()
                .replace(" ", "-");

        // Detect Technology
        var detectedType =
                detectionService
                        .detectTechnology(
                                projectRoot
                        );
                        model.addAttribute(
                        "technologyDetected",
                        true
                        );

        // Generate Dockerfile
        String dockerfilePath =
                dockerfileGenerationService
                        .generateDockerfile(
                                detectedType,
                                projectRoot
                        );
                        model.addAttribute(
                        "dockerfileGenerated",
                        true
                        );

        // Trigger Jenkins
        jenkinsTriggerService
                .triggerBuild(
                        projectRoot,
                        projectName
                );
                model.addAttribute(
                "jenkinsTriggered",
                true
                );

        // Temporary Build Status
        String buildStatus =
                jenkinsStatusService
                        .getBuildStatus();

        // UI Data
        model.addAttribute(
                "message",
                "GitHub Repository Deployment Started"
        );

        model.addAttribute(
                "repositoryUrl",
                repositoryUrl
        );

        model.addAttribute(
                "technology",
                detectedType
        );

        model.addAttribute(
                "projectRoot",
                projectRoot
        );

        model.addAttribute(
                "dockerfilePath",
                dockerfilePath
        );

        model.addAttribute(
                "buildStatus",
                buildStatus
        );

    }
    catch (Exception e) {

                model.addAttribute(
                        "deploymentError",
                        e.getMessage()
                );

                model.addAttribute(
                        "message",
                        "Deployment Failed"
                );

                model.addAttribute(
                        "buildStatus",
                        "FAILED"
                );
                }

    return "result-github";
}
}
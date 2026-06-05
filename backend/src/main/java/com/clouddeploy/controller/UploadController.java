package com.clouddeploy.controller;

import com.clouddeploy.service.DockerStatusService;
import com.clouddeploy.service.DockerfileGenerationService;
import com.clouddeploy.service.FileStorageService;
import com.clouddeploy.service.GitCloneService;
import com.clouddeploy.service.JenkinsStatusService;
import com.clouddeploy.service.JenkinsTriggerService;
import com.clouddeploy.service.KubernetesManifestService;
import com.clouddeploy.service.ProjectRootDetectionService;
import com.clouddeploy.service.TechStackDetectionService;
import com.clouddeploy.service.PortAllocationService;
import com.clouddeploy.service.KubernetesDeploymentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
private final FileStorageService fileStorageService;

private final ProjectRootDetectionService projectRootDetectionService;

private final TechStackDetectionService detectionService;

private final DockerfileGenerationService dockerfileGenerationService;

private final JenkinsTriggerService jenkinsTriggerService;

private final JenkinsStatusService jenkinsStatusService;

private final GitCloneService gitCloneService;

private final KubernetesManifestService kubernetesManifestService;

private final DockerStatusService dockerStatusService;

private final PortAllocationService portAllocationService;

private final KubernetesDeploymentService kubernetesDeploymentService;

public UploadController(
        FileStorageService fileStorageService,
        ProjectRootDetectionService projectRootDetectionService,
        TechStackDetectionService detectionService,
        DockerfileGenerationService dockerfileGenerationService,
        JenkinsTriggerService jenkinsTriggerService,
        JenkinsStatusService jenkinsStatusService,
        GitCloneService gitCloneService,
        KubernetesManifestService kubernetesManifestService,
        DockerStatusService dockerStatusService,
        PortAllocationService portAllocationService,
        KubernetesDeploymentService kubernetesDeploymentService

) {

    this.fileStorageService = fileStorageService;
    this.projectRootDetectionService = projectRootDetectionService;
    this.detectionService = detectionService;
    this.dockerfileGenerationService = dockerfileGenerationService;
    this.jenkinsTriggerService = jenkinsTriggerService;
    this.jenkinsStatusService = jenkinsStatusService;
    this.gitCloneService = gitCloneService;
    this.kubernetesManifestService = kubernetesManifestService;
    this.dockerStatusService = dockerStatusService;
    this.portAllocationService = portAllocationService;
    this.kubernetesDeploymentService = kubernetesDeploymentService;
}

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
                int hostPort =
                 portAllocationService
                        .getNextAvailablePort();
                int containerPort;
                switch (detectedType) {
                        case SPRING_BOOT:
                        case GRADLE_JAVA:
                                containerPort = 8080;
                                break;

                        case NODE_JS:
                        case REACT:
                                containerPort = 3000;
                                break;

                        case FLASK:
                                containerPort = 5000;
                                break;

                        case DJANGO:
                                containerPort = 8000;
                                break;

                        default:
                                containerPort = 8080;
                        }
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
                                String kubernetesAppName = "app-" + projectName;

                        kubernetesManifestService.generateManifests(
                                kubernetesAppName,
                                "clouddeploy-test",
                                containerPort
                                );

                                kubernetesDeploymentService.deploy(
                                kubernetesAppName
                                );

                        

            // STEP 5 - Trigger Jenkins
            System.out.println("PROJECT ROOT = " + projectRoot);
            jenkinsTriggerService.triggerBuild(
                                projectRoot,
                                projectName,
                                detectedType.toString(),
                                hostPort,
                                containerPort
                                );
                        System.out.println("================================");
System.out.println("HOST PORT = " + hostPort);
System.out.println("CONTAINER PORT = " + containerPort);
System.out.println("TECHNOLOGY = " + detectedType);
System.out.println("================================");
            model.addAttribute(
                "jenkinsTriggered",
                true
                );
            String buildStatus = jenkinsStatusService.getBuildStatus();
                        model.addAttribute(
                                "buildStatus",
                                buildStatus
                        );
         boolean running =
                        dockerStatusService
                        .isContainerRunning(
                        "clouddeploy-test-container"
                        );

                        model.addAttribute(
                        "dockerStatus",
                        running ? "Running" : "Stopped"
                        );

                        model.addAttribute(
                        "containerName",
                        "clouddeploy-test-container"
                        );

                        String applicationUrl =
                                "http://localhost:" + hostPort;

                                model.addAttribute(
                                "applicationUrl",
                                applicationUrl
                                );
                                model.addAttribute(
                                "hostPort",
                                hostPort
                                );

                                model.addAttribute(
                                "containerPort",
                                containerPort
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
                int hostPort =
                        portAllocationService
                        .getNextAvailablePort();

                        int containerPort;

                        switch (detectedType) {

                        case SPRING_BOOT:
                        case GRADLE_JAVA:
                        containerPort = 8080;
                        break;

                        case NODE_JS:
                        case REACT:
                        containerPort = 3000;
                        break;

                        case FLASK:
                        containerPort = 5000;
                        break;

                        case DJANGO:
                        containerPort = 8000;
                        break;

                        default:
                        containerPort = 8080;

}

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
                        kubernetesManifestService.generateManifests(
                        projectName,
                        "clouddeploy-test",
                        containerPort
                        );

                        kubernetesDeploymentService.deploy(
                        projectName
                        );


        // Trigger Jenkins
                jenkinsTriggerService.triggerBuild(
                        projectRoot,
                        projectName,
                        detectedType.toString(),
                        hostPort,
                        containerPort
                        );
                        String applicationUrl =
                        "http://localhost:" + hostPort;

                        model.addAttribute(
                        "applicationUrl",
                        applicationUrl
                        );

                        model.addAttribute(
                        "hostPort",
                        hostPort
                        );

                        model.addAttribute(
                        "containerPort",
                        containerPort
                        );

                        System.out.println("================================");
System.out.println("HOST PORT = " + hostPort);
System.out.println("CONTAINER PORT = " + containerPort);
System.out.println("TECHNOLOGY = " + detectedType);
System.out.println("================================");
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
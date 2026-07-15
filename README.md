<div align="center">

# ☁️ CloudDeploy

### 🚀 Generic DevOps Deployment Platform

**Upload. Detect. Build. Deploy.** — All in one click.

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?style=for-the-badge&logo=jenkins&logoColor=white)](https://www.jenkins.io/)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Orchestration-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)](https://kubernetes.io/)
[![GitHub](https://img.shields.io/badge/GitHub-Repo-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/)


![GitHub stars](https://img.shields.io/github/stars/PoobeshM/CloudDeploy?style=for-the-badge)

![GitHub forks](https://img.shields.io/github/forks/PoobeshM/CloudDeploy?style=for-the-badge)

![GitHub issues](https://img.shields.io/github/issues/PoobeshM/CloudDeploy?style=for-the-badge)

</div>

---

## 📖 Table of Contents

- [📌 Project Description](#-project-description)
- [🛠️ Technologies Used](#️-technologies-used)
- [✨ Major Features](#-major-features)
- [🧩 Supported Technologies](#-supported-technologies)
- [🏗️ Architecture](#️-architecture)
- [🔄 Complete Workflow](#-complete-workflow)
- [📂 Folder Structure](#-folder-structure)
- [⚙️ Installation](#️-installation)
- [🤖 Jenkins Section](#-jenkins-section)
- [🐳 Docker Section](#-docker-section)
- [☸️ Kubernetes Section](#️-kubernetes-section)
- [🚧 Future Enhancements](#-future-enhancements)
- [🎓 Learning Outcomes](#-learning-outcomes)
- [👨‍💻 Author](#-author)

---

## 📌 Project Description

**CloudDeploy** is a **Spring Boot based DevOps Automation Platform** that allows users to deploy applications by simply uploading a **ZIP file** or providing a **GitHub Repository URL**.

The platform automatically:

- 🔍 Detects the project technology
- 📁 Detects the project root
- 🐳 Generates a Dockerfile
- ☸️ Generates Kubernetes deployment manifests
- 🤖 Triggers a Jenkins CI/CD pipeline
- 🏗️ Builds Docker images
- ▶️ Runs Docker containers
- 🚀 Deploys applications to Kubernetes
- 📊 Displays deployment status
- 🔗 Displays application URL
- 📡 Shows Docker container status

> 💡 **In short:** CloudDeploy turns a raw source-code ZIP or GitHub link into a fully running, containerized, orchestrated application — with zero manual DevOps configuration.

---

## 🛠️ Technologies Used

<table>
<tr>
<td valign="top" width="25%">

### 🔧 Backend
- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf

</td>
<td valign="top" width="25%">

### 🏗️ Build Tool
- Maven

</td>
<td valign="top" width="25%">

### 🎨 Frontend
- HTML
- CSS
- Bootstrap
- JavaScript

</td>
<td valign="top" width="25%">

### ⚙️ DevOps
- Docker
- Jenkins
- Kubernetes

</td>
</tr>
</table>

### 🗂️ Version Control
- Git
- GitHub

---

## ✨ Major Features

| # | Feature |
|---|---------|
| 1️⃣ | ZIP Project Deployment |
| 2️⃣ | GitHub Repository Deployment |
| 3️⃣ | Automatic Technology Detection |
| 4️⃣ | Automatic Project Root Detection |
| 5️⃣ | Dockerfile Generation |
| 6️⃣ | Kubernetes Manifest Generation |
| 7️⃣ | Docker Image Build |
| 8️⃣ | Docker Container Deployment |
| 9️⃣ | Jenkins Pipeline Trigger |
| 🔟 | Dynamic Port Allocation |
| 1️⃣1️⃣ | Deployment Dashboard |
| 1️⃣2️⃣ | Docker Status Monitoring |
| 1️⃣3️⃣ | Application URL Generation |
| 1️⃣4️⃣ | Kubernetes Deployment Automation |

---

## 🧩 Supported Technologies

CloudDeploy automatically detects and supports the following project types:

| Technology | Detection | Dockerfile Generation | Kubernetes Deployment |
|-----------|:---------:|:----------------------:|:----------------------:|
| ☕ Spring Boot | ✅ | ✅ | ✅ |
| ☕ Java (Maven) | ✅ | ✅ | ✅ |
| 🐘 Gradle | ✅ | ✅ | ✅ |
| 🟩 Node.js | ✅ | ✅ | ✅ |
| ⚛️ React | ✅ | ✅ | ✅ |
| 🐍 Python | ✅ | ✅ | ✅ |
| 🌶️ Flask | ✅ | ✅ | ✅ |
| 🎯 Django | ✅ | ✅ | ✅ |
| 🌐 Static HTML | ✅ | ✅ | ✅ |

---

## 🏗️ Architecture

```
                    ┌────────────────────┐
                    │        👤 User     │
                    └──────────┬─────────┘
                               │
                               ▼
              ┌────────────────────────────────┐
              │   📦 Upload ZIP / GitHub URL   │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   🌱 Spring Boot Backend       │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   🔍 Technology Detection      │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   🐳 Dockerfile Generator      │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   ☸️ Kubernetes Manifest Ge    │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   🤖 Jenkins Pipeline          │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   🏗️ Docker Build              │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   📦 Docker Container          │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   ☸️ Kubernetes Deployment     │
              └──────────────┬─────────────────┘
                              │
                              ▼
              ┌────────────────────────────────┐
              │   🔗 Application URL           │
              └────────────────────────────────┘
```

---

## 🔄 Complete Workflow

```
📤 Upload Project
       │
       ▼
📂 Extract Project
       │
       ▼
🔍 Detect Technology
       │
       ▼
🐳 Generate Dockerfile
       │
       ▼
☸️ Generate Kubernetes YAML
       │
       ▼
🤖 Trigger Jenkins
       │
       ▼
🏗️ Build Project
       │
       ▼
📦 Build Docker Image
       │
       ▼
▶️ Deploy Docker Container
       │
       ▼
🚀 Deploy Kubernetes
       │
       ▼
📊 Show Deployment Status
       │
       ▼
🔗 Show Application URL
```

---

## 📂 Folder Structure

```
CloudDeploy/
│
├── 📁 backend/
│   ├── 📁 controller/          # REST & MVC Controllers
│   ├── 📁 service/             # Core business logic
│   ├── 📁 model/               # Entity & DTO classes
│   ├── 📁 repository/          # Data access layer
│   └── 📁 config/              # Application configuration
│
├── 📁 templates/                # Thymeleaf HTML templates
├── 📁 static/                   # CSS, JS, images
├── 📁 resources/                # application.properties / yml
│
├── 📁 docker-templates/         # Dockerfile templates per technology
├── 📁 kubernetes/               # deployment.yaml & service.yaml templates
├── 📁 generated/                # Auto-generated Dockerfiles & manifests
│
├── 🤖 Jenkinsfile               # CI/CD pipeline definition
└── 📄 README.md                 # You are here
```

---

## ⚙️ Installation

### ✅ Prerequisites

- ☕ Java 17+
- 🏗️ Maven 3.8+
- 🐳 Docker
- 🤖 Jenkins
- ☸️ Kubernetes (Dockerkube / Kind / Cloud Cluster)
- 🗂️ Git

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/PoobeshM/CloudDeploy.git
cd CloudDeploy
```

### 2️⃣ Build the Project

```bash
mvn clean install
```

### 3️⃣ Run the Application

```bash
mvn spring-boot:run
```

The application will start at 👉 `http://localhost:8087`
The port depends on your Spring Boot configuration.

<details>
<summary>🐳 <strong>Docker Installation</strong></summary>

```bash
# Install Docker (Ubuntu example)
sudo apt update
sudo apt install docker.io -y
sudo systemctl start docker
sudo systemctl enable docker

# Verify installation
docker --version
```

</details>

<details>
<summary>🤖 <strong>Jenkins Setup</strong></summary>

```bash
# Run Jenkins in Docker
docker run -d -p 8081:8080 -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  --name jenkins jenkins/jenkins:lts

# Access Jenkins at
http://localhost:8081
```

Unlock Jenkins using the initial admin password:

```bash
docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

</details>

<details>
<summary>☸️ <strong>Kubernetes Setup</strong></summary>

```bash

Enable Kubernetes in Docker Desktop

Settings
→ Kubernetes
→ Enable Kubernetes
→ Apply & Restart

Verify:
kubectl get nodes

```


</details>

---

## 🤖 Jenkins Section

CloudDeploy triggers a **parameterized Jenkins pipeline** for every deployment request.

### 📋 Pipeline Parameters

| Parameter | Description |
|-----------|-------------|
| `PROJECT_PATH` | Absolute path of the extracted/cloned project |
| `PROJECT_NAME` | Unique name assigned to the project/deployment |
| `TECHNOLOGY` | Detected technology stack (Spring Boot, Node.js, Python, etc.) |
| `HOST_PORT` | Dynamically allocated host port for the container |
| `CONTAINER_PORT` | Internal container port exposed by the application |

### 🔄 Jenkins Workflow

1. 📥 Receives deployment request with pipeline parameters from Spring Boot backend
2. 🏗️ Builds the project (Maven / npm / pip depending on technology)
3. 🐳 Builds the Docker image using the generated Dockerfile
4. ▶️ Runs the Docker container with dynamically allocated ports
5. ☸️ Applies Kubernetes manifests to the cluster
6. 📊 Reports build & deployment status back to the platform

---

## 🐳 Docker Section

<details>
<summary>📄 <strong>Dockerfile Generation</strong></summary>

CloudDeploy automatically generates a technology-specific `Dockerfile` based on the detected stack (Spring Boot, Node.js, Python, React, static HTML, etc.), including the correct base image, build steps, and entry point.

</details>

<details>
<summary>🏗️ <strong>Docker Image Build</strong></summary>

Once the Dockerfile is generated, Jenkins triggers `docker build` to produce a versioned image tagged with the project name.

</details>

<details>
<summary>▶️ <strong>Container Deployment</strong></summary>

The built image is run as a Docker container, with environment variables and volumes configured automatically based on project type.

</details>

<details>
<summary>🔌 <strong>Dynamic Port Allocation</strong></summary>

CloudDeploy scans for available host ports and dynamically assigns a free port to avoid conflicts between multiple deployments.

</details>

<details>
<summary>📡 <strong>Container Status Detection</strong></summary>

The dashboard continuously polls Docker to display real-time container status — running, stopped, or failed.

</details>

---

## ☸️ Kubernetes Section

<details>
<summary>📄 <strong>deployment.yaml</strong></summary>

Defines the Pod specification, replica count, container image, and resource limits for the application.

</details>

<details>
<summary>📄 <strong>service.yaml</strong></summary>

Exposes the deployed Pods internally or externally, enabling access to the running application via a stable endpoint.

</details>

<details>
<summary>⚙️ <strong>Automatic Manifest Generation</strong></summary>

CloudDeploy generates both `deployment.yaml` and `service.yaml` dynamically, filling in image name, ports, and labels based on the current project.

</details>

<details>
<summary>🚀 <strong>kubectl apply</strong></summary>

The generated manifests are applied to the cluster automatically using `kubectl apply -f`, without manual intervention.

</details>

### ☸️ Deployment Automation Flow

| Step | Action |
|------|--------|
| 1️⃣ | Generate `deployment.yaml` & `service.yaml` |
| 2️⃣ | Apply manifests via `kubectl apply` |
| 3️⃣ | 📦 Pod Creation |
| 4️⃣ | 🌐 Service Creation |
| 5️⃣ | 🔗 Expose Application URL |

---

## 🚧 Future Enhancements

- 🐳 Docker Hub Push
- ☁️ AWS EKS Support
- ☁️ Azure AKS Support
- ☁️ Google GKE Support
- ⛵ Helm Charts
- 🔄 ArgoCD Integration
- 🌍 Terraform Infrastructure as Code
- 📈 Monitoring Integration
- 📊 Grafana Dashboards
- 🔥 Prometheus Metrics
- 📜 Deployment Logs Viewer
- ⏪ Rollback Support
- 🔐 Authentication & Authorization
- 👥 Multi-user Support

---

## 🎓 Learning Outcomes

Building CloudDeploy demonstrates practical, hands-on understanding of:

- ⚙️ **Spring Boot** application architecture (MVC, layered services, Thymeleaf views)
- 🐳 **Docker fundamentals** — image building, containerization, and dynamic port management
- ☸️ **Kubernetes orchestration** — writing and applying deployment & service manifests
- 🤖 **CI/CD pipeline design** using Jenkins with parameterized builds
- 🔍 **Automated technology & project structure detection**
- 🧩 **End-to-end DevOps automation** — from source code to a live, running application
- 🗂️ **Version control workflows** with Git & GitHub
- 🖥️ **Full-stack development**, integrating a Java backend with a responsive Bootstrap frontend

---

## 👨‍💻 Author

<div align="center">

### **Poobesh M**

🎓 B.Tech Information Technology
🏫 Sri Shakthi Institute of Engineering and Technology

[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/PoobeshM)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](http://www.linkedin.com/in/poobesh-m-551826293)

</div>

---


<div align="center">

### ⭐ If you found this project useful, consider giving it a star!

</div>

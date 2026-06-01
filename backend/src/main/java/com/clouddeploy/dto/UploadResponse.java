package com.clouddeploy.dto;

public class UploadResponse {

    private String fileName;
    private String message;

    public UploadResponse() {
    }

    public UploadResponse(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
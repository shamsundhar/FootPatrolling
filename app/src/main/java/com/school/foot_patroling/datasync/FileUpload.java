package com.school.foot_patroling.datasync;

import java.util.Map;

public class FileUpload {


    public FileUpload(){

    }

    private Map<String,  String> fileData;

    public Map<String, String> getFileData() {
        return fileData;
    }

    public void setFileData(Map<String, String> fileData) {
        this.fileData = fileData;
    }

}

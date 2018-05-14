package com.liaojun.component.base.resource.dto;

import com.liaojun.component.base.resource.model.UploadFile;

import java.io.InputStream;

/**
 * Created by ChamIt-001 on 2017/11/23.
 */
public class UploadFileDto extends UploadFile {

    private String key;
    private InputStream inputStream;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public UploadFileDto setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }
}

package com.liaojun.component.base.http.model;

/**
 * Created by ChamIt-001 on 2018/1/18.
 */
public class HttpRequestParams {

    private String url;
    private String bodyContent;

    public String getUrl() {
        return url;
    }

    public HttpRequestParams setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public HttpRequestParams setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
        return this;
    }
}

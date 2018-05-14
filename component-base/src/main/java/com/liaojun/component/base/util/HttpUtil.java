package com.liaojun.component.base.util;

import com.liaojun.component.base.resource.dto.UploadFileDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ChamIt-001 on 2017/11/8.
 */
public class HttpUtil {

    public static enum CONTENT_TYPE {
        JSON("application/json;charset=utf-8");
        private String value;
        CONTENT_TYPE(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
    }

    public static String buildUrl(String url,Map<String,Object> params){
        try {
            return url + buildUrlParams(url,params);
        } catch (IOException e) {
            return null;
        }
    }

    public static String get(String url) throws IOException {
        String result = null;
        HttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(url));
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity,"UTF-8");
            }
        }
        return result;
    }

    public static String get(String url,Map<String,Object> params) throws IOException {
        return get(url + buildUrlParams(url,params));
    }

    public static String post(String url, Map<String,Object> params) throws IOException {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
        }
        if(list.size() > 0){
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
            httpPost.setEntity(entity);
        }
        HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity,"UTF-8");
            }
        }
        return result;
    }

    public static String post(String url, String paramString, CONTENT_TYPE contentType) throws IOException {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //设置参数
        httpPost.setEntity(new StringEntity(paramString, "UTF-8"));
        if(contentType != null) {
            httpPost.addHeader("Content-Type", contentType.getValue());
        }
        HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity,"UTF-8");
            }
        }
        return result;
    }

    public static String postMultiPart(String url, List<UploadFileDto> uploadFileDtoList, Map<String,Object> params) throws IOException{
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //设置参数
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Charset.forName("UTF-8")).setContentType(ContentType.MULTIPART_FORM_DATA);
        if(uploadFileDtoList != null) {
            for (UploadFileDto uploadFileDto : uploadFileDtoList) {
                entityBuilder.addBinaryBody(uploadFileDto.getKey(), uploadFileDto.getInputStream(), ContentType.MULTIPART_FORM_DATA, uploadFileDto.getFileName());
            }
        }
        if(params != null){
            for (Map.Entry<String,Object> paramEntry:params.entrySet()){
                entityBuilder.addTextBody(paramEntry.getKey(),paramEntry.getValue().toString());
            }
        }
        httpPost.setEntity(entityBuilder.build());
        HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity,"UTF-8");
            }
        }
        return result;
    }

    private static String buildUrlParams(String url,Map<String,Object> params) throws IOException{
        StringBuilder paramsBuilder = new StringBuilder();
        if(params != null && params.size()>0) {
            if (url.indexOf("?") >= 0) {
                paramsBuilder.append("&");
            } else {
                paramsBuilder.append("?");
            }
            for(Map.Entry<String,Object> entry:params.entrySet()){
                if(paramsBuilder.length() > 1) {
                    paramsBuilder.append("&");
                }
                paramsBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),"UTF-8"));
            }
        }
        return paramsBuilder.toString();
    }
}

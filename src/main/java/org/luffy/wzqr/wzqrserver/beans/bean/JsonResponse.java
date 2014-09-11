/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.beans.bean;

import java.io.UnsupportedEncodingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 *
 * @author luffy
 */
public class JsonResponse {

    public static final int SUCCESS = 200;
    public static final int FAILED = 400;
    public static final int ERROR = 500;

    private int code;
    private String originalMessage;

    public JsonResponse(int code) {
        this.code = code;
    }

    public JsonResponse(int code, String originalMessage) {
        this.code = code;
        this.originalMessage = originalMessage;
    }
    
    public HttpEntity toHttpEntity() throws UnsupportedEncodingException {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf("text/html;charset=UTF-8"));
        //{"code":200,"originalMessage":"上传成功！"}
        String json = String.format("{\"code\":%1d,\"originalMessage\":\"%2s\"}", this.getCode(),this.getOriginalMessage());        
        System.out.println(json);
        byte[] documentBody = json.getBytes("UTF-8");
        header.setContentLength(documentBody.length);
        return new HttpEntity<>(documentBody, header);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JsonResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

}

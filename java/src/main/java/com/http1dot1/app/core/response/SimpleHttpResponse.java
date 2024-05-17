package com.http1dot1.app.core.response;

import java.util.HashMap;
import java.util.Map;

/**
 * SimpleResponse
 */
public class SimpleHttpResponse {
    
    private int statusCode;
    private String statusMessage;
    private Map<String, String> headers;
    private String body;

    public SimpleHttpResponse() {
        this.headers = new HashMap<>();
    }

    public SimpleHttpResponse(int statusCode, String statusMessage, 
        Map<String, String> headers, String body) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = headers;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getHeaderVal(String key) {
        return this.headers.get(key);
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
        " statusCode="+statusCode +
        ", statusMessage="+statusMessage +
        ", headers="+headers+
        ", body="+body
        ;
    }
}

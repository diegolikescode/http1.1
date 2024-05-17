package com.http1dot1.app.core.request;

import java.util.Map;

/**
 * SimpleHttpRequest
 */
public class SimpleHttpRequest {

    private String method;
    private String path;
    private String protocol;
    private Map<String, String> headers;
    private String body;

    public SimpleHttpRequest(String method, String path, String protocol, 
        Map<String, String> headers, String body) {

        this.method = method;
        this.path = path;
        this.protocol = protocol;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeaderValue(String key) {
        return headers.get(key);
    }

    public String getBody() {
        return body;
    }
}

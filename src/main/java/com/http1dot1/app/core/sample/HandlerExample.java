package com.http1dot1.app.core.sample;

import java.util.HashMap;
import java.util.Map;

import com.http1dot1.app.core.handler.SimpleHandler;
import com.http1dot1.app.core.request.SimpleHttpRequest;
import com.http1dot1.app.core.response.SimpleHttpResponse;

/**
 * HandlerExample
 */
public class HandlerExample extends SimpleHandler {

    @Override
    public SimpleHttpResponse handle(SimpleHttpRequest request, SimpleHttpResponse response) {
        Map<String, String> customHeaders = new HashMap<>();

        customHeaders.put("custom_one", request.getHeaderValue("custom"));

        response.setHeaders(customHeaders);
        response.setStatusCode(200);
        response.setStatusMessage("OK");
        response.addHeader("Content-Type", "application/json");
        response.setBody("{\"aua\":\"a place to stay\"}");

        response.getBody().length();

        return response;
    }
}


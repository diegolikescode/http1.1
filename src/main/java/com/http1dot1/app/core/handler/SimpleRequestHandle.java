package com.http1dot1.app.core.handler;

import com.http1dot1.app.core.request.SimpleHttpRequest;
import com.http1dot1.app.core.response.SimpleHttpResponse;

/**
 * SimpleRequest
 */
public interface SimpleRequestHandle {

    public void handle(SimpleHttpRequest request, SimpleHttpResponse response);
}

package com.http1dot1.app.core.handler;

import com.http1dot1.app.core.request.SimpleHttpRequest;
import com.http1dot1.app.core.response.SimpleHttpResponse;

/**
 * SimpleHandler
 */
public abstract class SimpleHandler {

    /**
     * this is a sample of what a http handle looks like.
     * it will receive the request class already with the fields simplified and will use the response to build the response, returning it in the end.
     * when returning the SimpleHttpResponse class the HTTP implementation will take care of sending it back to the client
     */
    public abstract SimpleHttpResponse handle(SimpleHttpRequest request, SimpleHttpResponse response);
    
}

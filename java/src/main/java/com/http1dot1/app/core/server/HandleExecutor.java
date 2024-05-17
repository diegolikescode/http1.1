package com.http1dot1.app.core.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.http1dot1.app.core.handler.SimpleHandler;
import com.http1dot1.app.core.request.SimpleHttpRequest;
import com.http1dot1.app.core.response.SimpleHttpResponse;

/**
 * HandleExecutor
 * Each Executor will have its own handle
 */
public class HandleExecutor extends Thread {

    private Socket socket;
    private SimpleHandler handler;
    private SimpleHttpRequest request;
    private SimpleHttpResponse response;

    public HandleExecutor(Socket socket, SimpleHandler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    private void handleIt() {
        try (
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);) {

            buildRequest(input);

            SimpleHttpResponse response = handler.handle(this.request, new SimpleHttpResponse());

            response.addHeader("Content-Length", String.valueOf(response.getBody().length()));

            // TODO: deal with response shit

            output.println("HTTP/1.0 "+ response.getStatusCode() + " " + response.getStatusMessage());
            // write headers
            for (String key : response.getHeaders().keySet()) {
                output.println(key+": "+response.getHeaderVal(key));
            }
            // blank line indicates end of header and start of body
            output.println("");

            output.println(response.getBody());

            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildRequest(BufferedReader input) throws IOException {
        String[] requestLines = input.readLine().split(" ");
        String method = requestLines[0];
        String path = requestLines[1];
        String protocol = requestLines[2];

        Map<String, String> headers = buildHeaders(input);
        this.request = new SimpleHttpRequest(method, path, protocol, headers, buildRequestBody(input, headers.getOrDefault("Content-Length", "0")));
    }

    private Map<String, String> buildHeaders(BufferedReader input) throws IOException {
        Map<String, String> headersMap = new HashMap<>();
        String headLine;
        while ((headLine = input.readLine()) != null && !headLine.isEmpty()) {
            String[] headParts = headLine.split(": ", 2);
            headersMap.put(headParts[0], headParts[1]);
        }

        return headersMap;
    }

    private String buildRequestBody(BufferedReader input, String bodyLength) throws IOException {
        int length = Integer.parseInt(bodyLength);
        if (length <= 0) {
            return null;
        }

        StringBuilder reqBody = new StringBuilder();
        char[] buffer = new char[1024];
        int bytesRead;
        while(reqBody.length() < length && (bytesRead = input.read(buffer)) != -1) {
            reqBody.append(buffer, 0, bytesRead);
        }

        return reqBody.toString();
    }

    @Override
    public void run() {
        handleIt();
    }
}

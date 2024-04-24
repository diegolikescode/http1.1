package com.http1dot1.app.core.sample;

import java.util.*;

import com.http1dot1.app.core.handler.SimpleHandler;
import com.http1dot1.app.core.request.SimpleHttpRequest;
import com.http1dot1.app.core.response.SimpleHttpResponse;
import com.http1dot1.app.core.response.SimpleParser;

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
        BasicResponse basic = new BasicResponse("fooing", "barzing", 50);
        basic.setBasic(new BasicResponse("fooing", "barzing", 69));

        basic.setBasicList(new ArrayList<>(Arrays.asList(
            new BasicResponse("foozada", "barito", 969),
            new BasicResponse("forfigyht", "barito", 12312),
            new BasicResponse("foofighty", "barito", 3245),
            new BasicResponse("ahueauhewalk", "barista", 68686)
        )));

        response.setBody(basic.toJson());

        return response;
    }
}

class BasicResponse {
    private String foo;
    private String bar;
    private int baz;
    private BasicResponse basic;
    private List<BasicResponse> basicList;

    public BasicResponse(String foo, String bar, int baz) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public int getBaz() {
        return baz;
    }

    public void setBaz(int baz) {
        this.baz = baz;
    }

    public BasicResponse getBasic() {
        return basic;
    }

    public void setBasic(BasicResponse basic) {
        this.basic = basic;
    }

    public List<BasicResponse> getBasicList() {
        return basicList;
    }

    public void setBasicList(List<BasicResponse> basicList) {
        this.basicList = basicList;
    }


    public String toJson() {
        try {
            return SimpleParser.serializeToJson(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}


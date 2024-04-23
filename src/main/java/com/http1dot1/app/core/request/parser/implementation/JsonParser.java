package com.http1dot1.app.core.request.parser.implementation;

import com.http1dot1.app.core.request.parser.Parser;

/**
 * JsonParser
 */
public class JsonParser implements Parser<String> {

    private int current = 0;

    enum JsonTokens {
        OPEN_BRACKET("{"),
        CLOSE_BRACKET("}"),
        END_OF_JSON("EOF");

        private String val;

        JsonTokens(String val) {
            this.val=val;
        }
    }

    @Override
    public String parse(String rawData) {
        


        return null;
    }
    
    private void readJson(String rawJson) {
        for (String c : rawJson.split("")) {

        }

    }
}

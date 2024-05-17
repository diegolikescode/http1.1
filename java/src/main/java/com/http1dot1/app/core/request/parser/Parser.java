package com.http1dot1.app.core.request.parser;

/**
 * Parser
 *
 * RESPONSABILITIES::
 *     (a) Tokenization: categorizes the tokens of the input based on defined rules
 *     (b) Lexical Analysis: 
 *     (c) 
 *
 *
 */
public interface Parser<T> {
    public T parse(T data);
}


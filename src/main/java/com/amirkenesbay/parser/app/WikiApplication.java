package com.amirkenesbay.parser.app;

import com.amirkenesbay.parser.parsers.JsonParser;
import com.amirkenesbay.parser.requests.HttpGetRequest;

import java.io.IOException;

public class WikiApplication {
    public static void main(String[] args) {
        String request = HttpGetRequest.getRequest();
        String response = HttpGetRequest.getResponseFromWiki(request);
        String result = JsonParser.parseResponse(response);
        System.out.println(result);
//        JsonParser.showResult(result);
    }
}

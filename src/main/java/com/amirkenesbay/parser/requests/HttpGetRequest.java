package com.amirkenesbay.parser.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpGetRequest {
    private final static String URL = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=\"Java\"";

    public static String getRequest(){
        return URL;
    }

    public static String getResponseFromWiki(String urlAddressFromWiki){
        HttpURLConnection connection = null;
        URL url = null;
        InputStreamReader isR = null;
        BufferedReader bfR = null;
        URLEncoder urlEncoder = null;
        try {
            url = new URL(urlAddressFromWiki);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(6 * 10 * 1000);
            connection.setReadTimeout(6 * 10 * 1000);
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                isR = new InputStreamReader(connection.getInputStream());
                bfR = new BufferedReader(isR);
                String line;
                while ((line = bfR.readLine()) != null) {
                    return line;
                }
            } else {
                System.out.printf("Fail %s", connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bfR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

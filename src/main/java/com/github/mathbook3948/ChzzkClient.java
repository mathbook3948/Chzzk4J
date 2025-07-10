package com.github.mathbook3948;

import org.eclipse.jetty.client.HttpClient;

public class ChzzkClient {

    private final String baseUrl = "https://openapi.chzzk.naver.com";

    private final HttpClient client;

    public ChzzkClient() throws Exception {
        HttpClient client = new HttpClient();
        client.start();
        this.client = client;
    }
}

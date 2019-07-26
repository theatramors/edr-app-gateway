package com.amors.edrappgateway.http;

import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Class for HTTP requests
 *
 * @author theatramors
 */
public class HttpClient {

    private final Logger logger = getLogger(HttpClient.class);

    private String url;

    public HttpClient() {
    }

    public HttpClient(String url) {
        this.url = url;
    }

    /**
     * Makes HTTP GET request
     *
     * @return response body as string
     */
    public String get() throws IOException {
        return get(this.url, new HashMap<>());
    }

    /**
     * Makes HTTP GET request
     *
     * @param headers HTTP headers
     * @return response body as string
     */
    public String get(Map<String, String> headers) throws IOException {
        return get(this.url, headers);
    }

    /**
     * Makes HTTP GET request
     *
     * @param url     url
     * @param headers HTTP headers
     * @return response body as string
     */
    public String get(String url, Map<String, String> headers) throws IOException {
        logger.info("Sending GET request to {}", url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        headers.forEach(connection::setRequestProperty);
        InputStream inputStream;

        try {
            inputStream = connection.getInputStream();
        } catch (IOException ex) {
            inputStream = connection.getErrorStream();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder response = new StringBuilder();
        bufferedReader.lines().forEach(line -> {
            response.append(line.trim());
            logger.info("Request response = {}", response);
        });

        return response.toString();
    }
}
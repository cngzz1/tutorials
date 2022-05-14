package com.baeldung.httpclient.readresponsebodystring;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;

public class HttpUrlConnectionUnitTest {

    public static final String DUMMY_URL = "https://postman-echo.com/get";

    @Test
    public void whenUseHttpUrlConnection_thenCorrect() throws IOException {
        final URLConnection connection = new URL(DUMMY_URL).openConnection();
        final StringBuilder response;
        try (final InputStream inputStream = Objects.requireNonNull(connection).getInputStream()) {
            try (final BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
                response = new StringBuilder();
                in.lines().forEach(response::append);
            }
        }
        assertNotNull(response.toString());
        System.out.println("Response -> " + response.toString());
    }
}

package com.baeldung.httpclient.readresponsebodystring;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApacheHttpClient5UnitTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String DUMMY_URL = "https://postman-echo.com/get";

    @Test
    public void whenUseApacheHttpClient_thenCorrect() throws IOException, ParseException {
        final HttpGet request = new HttpGet(DUMMY_URL);

        try (final CloseableHttpClient client = HttpClients.createDefault()) {
            try (final CloseableHttpResponse response = Objects.requireNonNull(client).execute(Objects.requireNonNull(request))) {
                final HttpEntity entity = Objects.requireNonNull(response).getEntity();
                logger.debug("Response -> {}", EntityUtils.toString(entity));
            }
        }
        assertEquals(HttpGet.class, request.getClass());
    }
}
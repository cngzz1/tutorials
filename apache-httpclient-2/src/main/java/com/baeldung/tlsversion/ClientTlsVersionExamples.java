package com.baeldung.tlsversion;

import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

public class ClientTlsVersionExamples {
    private static final String TLS_V3 = "TLSv1.3";
    public static CloseableHttpClient setViaSocketFactory() {
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
          SSLContexts.createDefault(),
          new String[] { "TLSv1.2", TLS_V3 },
          null,
          SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    public static CloseableHttpClient setTlsVersionPerConnection() {
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContexts.createDefault()) {

            @Override
            protected void prepareSocket(SSLSocket socket) {
                String hostname = Objects.requireNonNull(socket).getInetAddress().getHostName();
                if (Objects.requireNonNull(hostname).endsWith("internal.system.com")) {
                    Objects.requireNonNull(socket).setEnabledProtocols(new String[] { "TLSv1", "TLSv1.1", "TLSv1.2", TLS_V3 });
                } else {
                    Objects.requireNonNull(socket).setEnabledProtocols(new String[] { TLS_V3 });
                }
            }
        };

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    // To configure the TLS versions for the client, set the https.protocols system property during runtime.
    // For example: java -Dhttps.protocols=TLSv1.1,TLSv1.2,TLSv1.3 -jar webClient.jar
    public static CloseableHttpClient setViaSystemProperties() {
        return HttpClients.createSystem();
    }

    public static void main(String[] args) throws IOException {
        try (final CloseableHttpClient httpClient = setViaSocketFactory();
           final CloseableHttpResponse response = Objects.requireNonNull(httpClient).execute(new HttpGet("https://httpbin.org/"))) {

            final HttpEntity entity = Objects.requireNonNull(response).getEntity();
            EntityUtils.consume(Objects.requireNonNull(entity));
        }
    }
}
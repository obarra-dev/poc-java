package com.obarra;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientTestIT
{
  static final String IT_HOST = "it.host";

  static final String IT_PORT = "it.port";

  protected static URI uri;


  @BeforeClass
  public static void setupUri() throws Exception {
    String host = System.getProperty(IT_HOST);
    String port = System.getProperty(IT_PORT);
    assertNotNull(
        "Unable to load server port from system property '" + IT_PORT + "'", port);
    uri = new URI("http://" + host + ":" + port);
  }

  @Test
  public void testApi() throws Exception {
    String responseBody = getData();

    assertEquals("{\n" +
        "  \"message\": \"Hello from your simple API!\",\n" +
        "  \"status\": \"success\",\n" +
        "  \"timestamp\": \"2025-04-04T22:25:00Z\"\n" +
        "}\n", responseBody);
  }


  private String getData() throws Exception {
    HttpGet get = new HttpGet(uri.resolve("/data"));
    get.setHeader("Accept", "application/json");
    CloseableHttpClient http = HttpClientBuilder.create().build();
    return http.execute(get, new BasicResponseHandler());
  }

}

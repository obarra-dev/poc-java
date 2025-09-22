package com.obarra;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

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
  public void test() throws Exception {
    String responseBody = getRepos("/service/rest/v1/repositories");
  }


  private String getRepos(final String query) throws Exception {
    HttpGet get = new HttpGet(uri.resolve(query));
    get.setHeader("Accept", "application/json");
    CloseableHttpClient http = HttpClientBuilder.create().build();
    return http.execute(get, new BasicResponseHandler());
  }

}

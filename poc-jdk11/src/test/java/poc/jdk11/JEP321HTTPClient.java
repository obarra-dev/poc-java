package poc.jdk11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

class JEP321HTTPClient {

    /**
     * ofXxx when we are creating pre-defined handlers/subscribers.
     */
    @Test
    void httpClientSendPost() throws InterruptedException, IOException, URISyntaxException {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        var url = getClass().getClassLoader().getResource("post.json");
        var request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(url.toURI())))
                .uri(URI.create("https://httpbin.org/post"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void httpClientSend() throws InterruptedException, IOException {
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://en.wikipedia.org/w/rest.php/v1/page/Golang"))
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response.body());
    }

    @Test
    void httpClientSendAsync() {
        var httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://en.wikipedia.org/w/rest.php/v1/page/Golang"))
                .build();
        var asyncResponse = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        asyncResponse.thenApply(HttpResponse::body)
                .thenAccept(Assertions::assertNotNull);

        asyncResponse.thenApply(HttpResponse::statusCode)
                .thenAccept(s -> Assertions.assertEquals(200, s));
    }

    //TODO  Handling Push Promises in HTTP/2
}

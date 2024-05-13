package http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;

public class Get {
    String apiUrl;

    public Get(String apiUrl){
        this.apiUrl = apiUrl;
    }

    public HttpResponse doRequest() throws IOException, InterruptedException {
        HttpClient client = newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}

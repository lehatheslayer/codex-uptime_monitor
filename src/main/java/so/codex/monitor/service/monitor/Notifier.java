package so.codex.monitor.service.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Класс, который просит codex бота оповестить о проблеме в телеграм
 */
@Component
@PropertySource("classpath:application.properties")
public class Notifier {
    @Value("${webhook}")
    private String webHook;

    public void sendAlert(String message) {
        System.out.println(webHook);
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webHook))
                    .POST(HttpRequest.BodyPublishers.ofString("message=" + message))
                    .setHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
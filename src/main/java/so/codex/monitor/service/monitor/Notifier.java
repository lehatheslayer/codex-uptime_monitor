package so.codex.monitor.service.monitor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Класс, который просит codex бота оповестить о проблеме в телеграм
 */
public class Notifier {
    public static void sendAlert(String message) {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(XMLParser.getWebHook()))
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
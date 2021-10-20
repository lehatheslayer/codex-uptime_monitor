package so.codex.monitor.service.monitor;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

import so.codex.monitor.models.Status;

/**
 * Класс, который пингует сайты
 */
public class RequestSender {
    private final static String URL_PREFIX = "https://";

    /**
     * Адреса, которые нужно пропинговать
     */
    private final List<String> addresses;
    private final HttpClient httpClient;
    private final List<HttpRequest> httpRequests;

    public RequestSender() {
        this.addresses = XMLParser.getUrls();
        this.httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        this.httpRequests = new ArrayList<>();

        createHttpPosts();
    }

    private void createHttpPosts() {
        try {
            for (var url : addresses) {
                final HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
                httpRequests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Пингует сайты из addresses, записывает время отклика, код и размер ответа в объект класса Status
     *
     * @return объект класса Status
     */
    public List<Status> sendRequest() throws InterruptedException, IOException {
        final List<Status> result = new ArrayList<>();

        for (var httpRequest : httpRequests) {
            final long time1 = System.currentTimeMillis();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            final long requestTime = System.currentTimeMillis() - time1;
            final long requestWeight = response.body().length();
            final int requestCode = response.statusCode();

            result.add(new Status(
                        URL_PREFIX + httpRequest.uri().getHost() + httpRequest.uri().getPath(),
                        requestTime,
                        requestWeight,
                        requestCode));
        }

        return result;
    }
}

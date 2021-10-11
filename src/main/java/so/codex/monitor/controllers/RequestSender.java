package so.codex.monitor.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.SAXException;

import org.apache.http.client.HttpClient;
import so.codex.monitor.models.Status;

public class RequestSender {
    private List<String> addresses;
    private HttpClient httpClient;
    private List<HttpPost> httpPosts;

    public RequestSender() throws ParserConfigurationException, SAXException, IOException {
        init();
        createHttpPosts();
    }

    public RequestSender(List<HttpPost> httpPosts) throws ParserConfigurationException, SAXException, IOException {
        init();
        this.httpPosts = httpPosts;
    }

    private void init() throws IOException, SAXException, ParserConfigurationException {
        this.addresses = XMLParser.getAddresses();
        this.httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build()).build();
        this.httpPosts = new ArrayList<>();
    }

    private void createHttpPosts() {
        try {
            for (var url : addresses) {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(new ArrayList<BasicNameValuePair>(), "UTF-8"));
                httpPosts.add(httpPost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Status> sendRequest() {
        try {
            List<Status> result = new ArrayList<>();

            for (var httpPost : httpPosts) {
                final long time1 = System.currentTimeMillis();
                final HttpResponse response = httpClient.execute(httpPost);

                final long requestTime = System.currentTimeMillis() - time1;
                final long requestWeight = response.getEntity().getContentLength();
                final int requestCode = response.getStatusLine().getStatusCode();

                result.add(new Status(
                            httpPost.getURI().getHost(),
                            requestTime,
                            requestWeight,
                            requestCode));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>() {{
            add(new Status("none", 0, 0, 0));
        }};
    }
}

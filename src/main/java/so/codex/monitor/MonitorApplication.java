package so.codex.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;
import so.codex.monitor.controllers.RequestSender;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
        MonitorTask m1=ApplicationContextProvider.getBean("mTask",MonitorTask.class);
        m1.start();

//        String url = "https://codex.so/";
//
//        try {
//            RequestSender requestSender = new RequestSender();
//            for (int i = 0; i < 5; i++) {
//                System.out.println(requestSender.sendRequest());
//            }
//        } catch (IOException | SAXException | ParserConfigurationException e) {
//            e.printStackTrace();
//        }
    }

}

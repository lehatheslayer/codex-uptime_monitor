package so.codex.monitor.service.monitor.task;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import so.codex.monitor.service.monitor.Notifier;
import so.codex.monitor.service.monitor.RequestSender;
import so.codex.monitor.service.monitor.XMLParser;
import so.codex.monitor.models.Status;
import so.codex.monitor.service.impl.StatusServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс-поток, который на фоне будет пинговать сайты
 */
@Component("mTask")
@Scope("prototype")
public class MonitorTask extends Thread {
    private static final int EXPECTED_CODE = 200;
    private static final Map<String, Pair> MIDDLE_WEIGHT_AND_TIMEOUT = new HashMap<>();
    private static final int DATA_COLLECTION_ITERATIONS = 50;

    public final static long TIME_OUT = 300000L;

    private final ApplicationContext notifierContext = new AnnotationConfigApplicationContext(
            "so.codex.monitor.service.monitor");
    private final Notifier notifier = notifierContext.getBean(Notifier.class);

    private final RequestSender requestSender = new RequestSender();

    @Autowired
    StatusServiceImpl statusService;

    /**
     * Метод пингует сайты каждые TIMEOUT миллисекунд.
     * Первые DATA_COLLECTION_ITERATIONS итераций собирает среднее у веса ответа и времени отклика.
     * Далее, если вес ответа или время отклика отличается на определенный процент от средних данных, то присутствует
     * какая-то проблема -> метод вызывает sendAlarm(String message), который оповещает о проблеме.
     * Если код ответа отличен от EXPECTED_CODE, то это означает, что присутствует какая-то проблема.
     */
    @Override
    public void run() {
        int it_num = 0;

        while (true) {
            try {
                final List<Status> statuses = statusService.addStatuses(requestSender.sendRequest());
                final Map<String, List<Pair>> weight_and_timeout = new HashMap<>();

                if (it_num < DATA_COLLECTION_ITERATIONS) {
                    it_num++;

                    for (var status : statuses) {
                        List<Pair> tmp = new ArrayList<>();
                        tmp.add(new Pair(status.getRequest_weight(), status.getRequest_time()));

                        weight_and_timeout.put(status.getUrl(), tmp);

                        if (status.getRequest_code() != EXPECTED_CODE) {
                            sendAlarm("Provided status code for " + status.getUrl()
                                    + ": " + status.getRequest_code()
                                    + "\nExpected: " + EXPECTED_CODE);
                        }
                    }

                    if (it_num == DATA_COLLECTION_ITERATIONS) {
                        calculateMiddleWeightAndTimeout(weight_and_timeout);
                    }
                } else {
                    for (var status : statuses) {
                        if (status.getRequest_code() != EXPECTED_CODE) {
                            sendAlarm("Provided status code for " + status.getUrl()
                                    + ": " + status.getRequest_code()
                                    + "\nExpected: " + EXPECTED_CODE);
                        }

                        if (status.getRequest_weight() < MIDDLE_WEIGHT_AND_TIMEOUT.get(status.getUrl()).getFirst()) {
                            sendAlarm("asd");
                        }

                        if (status.getRequest_time() < MIDDLE_WEIGHT_AND_TIMEOUT.get(status.getUrl()).getSecond()) {
                            sendAlarm("asd");
                        }
                    }
                }

                sleep(TIME_OUT);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод отправляет оповещение через телеграм бота
     *
     * @param message - текст оповещения
     */
    private void sendAlarm(String message) {
        notifier.sendAlert(message);
    }

    /**
     * Вычисляет усредненные вес ответа и время отклика по DATA_COLLECTION_ITERATIONS итерациям.
     *
     * @param weight_and_timeout - мапа, ключи которого являются адресами, а значения - вес и время отклика за
     * DATA_COLLECTION_ITERATIONS итераций
     */
    private void calculateMiddleWeightAndTimeout(Map<String, List<Pair>> weight_and_timeout) {
        for (var url : XMLParser.getUrls()) {
            var list = weight_and_timeout.get(url);

            final Pair middle = new Pair(0L, 0L);
            for (var pair : list) {
                middle.sumFirst(pair.getFirst());
                middle.sumSecond(pair.getSecond());
            }

            MIDDLE_WEIGHT_AND_TIMEOUT.put(url, middle);
        }
    }
}

@Data
class Pair {
    private Long first;
    private Long second;

    public Pair(Long t, Long s) {
        this.first = t;
        this.second = s;
    }

    public void sumFirst(Long first) {
        this.first += first;
    }

    public void sumSecond(Long second) {
        this.second += second;
    }
}

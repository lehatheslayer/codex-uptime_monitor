package so.codex.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import so.codex.monitor.service.monitor.task.ApplicationContextProvider;
import so.codex.monitor.service.monitor.task.MonitorTask;

@SpringBootApplication
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
        MonitorTask m1 = ApplicationContextProvider.getBean("mTask", MonitorTask.class);
        m1.start();
    }
}

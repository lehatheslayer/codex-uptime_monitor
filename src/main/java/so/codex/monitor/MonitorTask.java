package so.codex.monitor;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import so.codex.monitor.controllers.RequestSender;
import so.codex.monitor.service.StatusService;

import javax.management.monitor.Monitor;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Component("mTask")
@Scope("prototype")
public class MonitorTask extends Thread {
    final static Logger logger= LoggerFactory.getLogger(MonitorTask.class);

    private RequestSender requestSender = new RequestSender();
    private Monitor monitor;

    @Autowired
    StatusService statusService;

    public MonitorTask() throws ParserConfigurationException, SAXException, IOException {
    }

    public void setMonitor(Monitor monitor) throws ParserConfigurationException, SAXException, IOException {
        this.requestSender = new RequestSender();
        this.monitor=monitor;
    }

    @SneakyThrows
    @Override
    public void run(){
        while (true) {
            statusService.addStatuses(requestSender.sendRequest());
            sleep(3000);
        }
    }
}

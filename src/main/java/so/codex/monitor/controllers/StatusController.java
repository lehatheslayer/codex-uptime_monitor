package so.codex.monitor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class StatusController {
    private final RequestSender requestSender = new RequestSender();

    public StatusController() throws ParserConfigurationException, SAXException, IOException {
    }

    @GetMapping("/")
    public String status(Model model) {
        return "status";
    }
}

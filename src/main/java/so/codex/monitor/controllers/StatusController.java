package so.codex.monitor.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import so.codex.monitor.service.impl.StatusServiceImpl;

@RestController
public class StatusController {
    private final ObjectMapper objectMapper = new ObjectMapper() {{
        enable(SerializationFeature.INDENT_OUTPUT);
    }};

    @Autowired
    private StatusServiceImpl statusService;

    @GetMapping("/getPoints")
    public Map<String, String> status(Model model) throws JsonProcessingException {
        final var it = statusService.getAll();
        return new HashMap<>() {{
            put("result", objectMapper.writeValueAsString(it));
        }};
    }
}



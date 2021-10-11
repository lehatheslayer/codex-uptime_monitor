package so.codex.monitor.models;

import lombok.Data;

@Data
public class StatusDTO {
    private long request_time;
    private long request_weight;
    private int request_code;
}

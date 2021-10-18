package so.codex.monitor.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String url;

    private long request_time;
    private long request_weight;

    private int request_code;

    public Status(String url, long request_time, long request_weight, int request_code) {
        this.url = url;
        this.request_time = request_time;
        this.request_weight = request_weight;
        this.request_code = request_code;
    }
}

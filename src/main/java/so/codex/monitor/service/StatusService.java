package so.codex.monitor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import so.codex.monitor.models.Status;

import java.util.List;
import java.util.Map;

public interface StatusService {
    Status add(Status status);

    List<Status> addStatuses(List<Status> statuses);

    Iterable<Status> getAll();
}

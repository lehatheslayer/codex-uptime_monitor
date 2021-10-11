package so.codex.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import so.codex.monitor.models.Status;
import so.codex.monitor.repo.StatusRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    public List<Status> addStatuses(List<Status> statuses) {
        List<Status> result = new ArrayList<>();
        for (var status : statuses) {
            result.add(statusRepository.save(status));
        }

        return result;
    }
}

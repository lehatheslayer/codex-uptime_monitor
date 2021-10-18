package so.codex.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import so.codex.monitor.models.Status;
import so.codex.monitor.repository.StatusRepository;
import so.codex.monitor.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public Status add(Status status) {
        return statusRepository.save(status);
    }

    public List<Status> addStatuses(List<Status> statuses) {
        final List<Status> result = new ArrayList<>();
        for (var status : statuses) {
            result.add(statusRepository.save(status));
        }

        return result;
    }

    public Iterable<Status> getAll() {
        return statusRepository.findAll();
    }

}

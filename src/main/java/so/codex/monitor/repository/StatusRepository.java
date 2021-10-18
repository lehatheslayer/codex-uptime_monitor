package so.codex.monitor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import so.codex.monitor.models.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}

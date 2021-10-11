package so.codex.monitor.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import so.codex.monitor.models.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}

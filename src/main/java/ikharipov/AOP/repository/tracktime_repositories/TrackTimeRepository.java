package ikharipov.AOP.repository.tracktime_repositories;

import ikharipov.AOP.model.tracktime.MethodTimeExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackTimeRepository extends JpaRepository<MethodTimeExecution, UUID> {
    List<MethodTimeExecution> getAllByMethodName(String methodName);
}

package my.code.store.repositories;

import my.code.store.entities.TaskEntity;
import my.code.store.entities.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStateRepository extends JpaRepository<TaskStateEntity, Long> {
}

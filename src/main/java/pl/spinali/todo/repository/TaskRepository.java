package pl.spinali.todo.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spinali.todo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

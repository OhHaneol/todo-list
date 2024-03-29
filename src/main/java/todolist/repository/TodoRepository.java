package todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}

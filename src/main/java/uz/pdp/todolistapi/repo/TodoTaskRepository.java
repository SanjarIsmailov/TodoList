package uz.pdp.todolistapi.repo;

import org.springframework.data.repository.CrudRepository;
import uz.pdp.todolistapi.entity.TodoTask;

public interface TodoTaskRepository extends CrudRepository<TodoTask, Integer> {
}

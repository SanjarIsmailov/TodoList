package uz.pdp.todolistapi.repo;

import org.springframework.data.repository.CrudRepository;
import uz.pdp.todolistapi.entity.TodoTaskChange;

public interface TodoTaskChangeRepository extends CrudRepository<TodoTaskChange, Integer> {
}

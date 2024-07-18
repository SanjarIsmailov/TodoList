package uz.pdp.todolistapi.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uz.pdp.todolistapi.entity.TodoTaskChange;
import uz.pdp.todolistapi.event.TodoTaskEvent;
import uz.pdp.todolistapi.repo.TodoTaskChangeRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TodoTaskEventListener {
    private final TodoTaskChangeRepository todoTaskChangeRepository;

    @EventListener
    public void handleTodoTaskEvent(TodoTaskEvent event) {
        TodoTaskChange change = new TodoTaskChange();
        change.setTaskId(event.getTaskId());
        change.setChangeType(event.getChangeType());
        change.setDescription(event.getDescription());
        change.setChangeTime(LocalDateTime.now());
        todoTaskChangeRepository.save(change);
    }
}

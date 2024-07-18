package uz.pdp.todolistapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import uz.pdp.todolistapi.entity.TodoTask;
import uz.pdp.todolistapi.event.TodoTaskEvent;
import uz.pdp.todolistapi.repo.TodoTaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoTaskService {
    private final TodoTaskRepository todoTaskRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<TodoTask> getAllTasks() {
        return (List<TodoTask>) todoTaskRepository.findAll();
    }

    public Optional<TodoTask> getTaskById(Integer id) {
        return todoTaskRepository.findById(id);
    }

    public TodoTask createTask(TodoTask task) {
        TodoTask savedTask = todoTaskRepository.save(task);
        eventPublisher.publishEvent(new TodoTaskEvent(this, savedTask.getId(), "CREATED", "Task created"));
        return savedTask;
    }

    public TodoTask updateTask(Integer id, TodoTask updatedTask) {
        Optional<TodoTask> existingTaskOpt = todoTaskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            TodoTask existingTask = existingTaskOpt.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            TodoTask savedTask = todoTaskRepository.save(existingTask);
            eventPublisher.publishEvent(new TodoTaskEvent(this, savedTask.getId(), "UPDATED", "Task updated"));
            return savedTask;
        }
        return null;
    }

    public void deleteTask(Integer id) {
        todoTaskRepository.deleteById(id);
        eventPublisher.publishEvent(new TodoTaskEvent(this, id, "DELETED", "Task deleted"));
    }
}

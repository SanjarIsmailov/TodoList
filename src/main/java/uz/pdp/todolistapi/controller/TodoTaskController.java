package uz.pdp.todolistapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todolistapi.entity.TodoTask;
import uz.pdp.todolistapi.service.TodoTaskService;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TodoTaskController {
    private final TodoTaskService todoTaskService;

    @GetMapping
    public String getAllTasks(Model model) {
        List<TodoTask> tasks = todoTaskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", new TodoTask());
        return "index";
    }

    @PostMapping
    public String createTask(@ModelAttribute TodoTask task) {
        todoTaskService.createTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam Integer id, @RequestParam boolean completed) {
        TodoTask task = todoTaskService.getTaskById(id).orElseThrow();
        task.setCompleted(completed);
        todoTaskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam Integer id) {
        todoTaskService.deleteTask(id);
        return "redirect:/tasks";
    }
}

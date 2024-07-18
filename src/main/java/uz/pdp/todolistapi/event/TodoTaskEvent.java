package uz.pdp.todolistapi.event;

import org.springframework.context.ApplicationEvent;

public class TodoTaskEvent extends ApplicationEvent {
    private final Integer taskId;
    private final String changeType;
    private final String description;

    public TodoTaskEvent(Object source, Integer taskId, String changeType, String description) {
        super(source);
        this.taskId = taskId;
        this.changeType = changeType;
        this.description = description;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getChangeType() {
        return changeType;
    }

    public String getDescription() {
        return description;
    }
}

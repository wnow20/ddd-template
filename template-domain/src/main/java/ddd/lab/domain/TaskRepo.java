package ddd.lab.domain;

import java.util.Optional;
import java.util.UUID;

import ddd.lab.jpa.TaskRepository;
import ddd.lab.jpa.TaskDataObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ge
 * @date 2021/11/17
 */
@Component
public class TaskRepo {
    private final TaskRepository repository;

    @Autowired
    public TaskRepo(TaskRepository repository) {
        this.repository = repository;
    }

    public Task get(UUID id) {
        Optional<TaskDataObject> optional = repository.findById(id);

        return optional.map(this::deSerialize)
            .orElse(null);
    }

    public UUID save(Task task) {
        TaskDataObject dataObject = this.serialize(task);
        repository.save(dataObject);
        return task.getId();
    }

    private TaskDataObject serialize(Task task) {
        TaskDataObject dataObject = new TaskDataObject();
        dataObject.setId(task.getId());
        dataObject.setTitle(task.getTitle());
        dataObject.setCompleted(task.getCompleted());
        return dataObject;
    }

    private Task deSerialize(TaskDataObject dataObject) {
        Task task = new Task(dataObject.getId(), dataObject.getTitle());
        task.setCompleted(dataObject.getCompleted());
        return task;
    }
}

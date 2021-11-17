package ddd.lab.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import ddd.lab.domain.Task;
import ddd.lab.domain.TaskRepo;
import ddd.lab.jpa.TaskDataObject;
import ddd.lab.jpa.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author ge
 * @date 2021/11/17
 */
@Component
public class TaskApplicationService {
    private final TaskRepo taskRepo;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskApplicationService(TaskRepo taskRepo,
                                  TaskRepository taskRepository) {
        this.taskRepo = taskRepo;
        this.taskRepository = taskRepository;
    }

    public Task get(UUID id) {
        return taskRepo.get(id);
    }

    public UUID addTask(TaskAddCommand taskAddCommand) {
        Task task = new Task(taskAddCommand.getTitle());
        task.setCompleted(false);
        return taskRepo.save(task);
    }

    public void completeTask(TaskCompleteCommand taskCompleteCommand) {
        Task task = taskRepo.get(taskCompleteCommand.getId());
        Assert.notNull(task, "todo item not found.");

        task.complete();
        taskRepo.save(task);
    }

    public List<TaskRepresentation> listQuery(TaskListQuery taskListQuery) {
        Iterable<TaskDataObject> iterable = taskRepository.findAllByTitleStartsWith(taskListQuery.getKeyword());
        return StreamSupport.stream(iterable.spliterator(), false)
            .map(this::convert)
            .collect(Collectors.toList());
    }

    private TaskRepresentation convert(TaskDataObject taskDataObject) {
        TaskRepresentation taskRepresentation = new TaskRepresentation();
        taskRepresentation.setId(taskDataObject.getId());
        taskRepresentation.setTitle(taskDataObject.getTitle());
        taskRepresentation.setCompleted(taskDataObject.getCompleted());
        return taskRepresentation;
    }
}

package ddd.lab.application;

import java.util.List;
import java.util.UUID;

import ddd.lab.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ge
 * @date 2021/11/17
 */
@SpringBootTest
class TaskApplicationServiceTest {
    @Autowired
    private TaskApplicationService taskApplicationService;
    @Test
    void should_add_task_correctly() {
        TaskAddCommand taskAddCommand1 = new TaskAddCommand();
        taskAddCommand1.setTitle("task1");
        UUID taskId1 = taskApplicationService.addTask(taskAddCommand1);
        Task task = taskApplicationService.get(taskId1);
        assertThat(task.getTitle()).isEqualTo("task1");
    }

    @Test
    void should_complete_task_correctly() {
        TaskAddCommand taskAddCommand1 = new TaskAddCommand();
        taskAddCommand1.setTitle("task1");

        UUID taskId1 = taskApplicationService.addTask(taskAddCommand1);
        Task task = taskApplicationService.get(taskId1);
        assertThat(task.getCompleted()).isFalse();

        TaskCompleteCommand taskCompleteCommand = new TaskCompleteCommand();
        taskCompleteCommand.setId(taskId1);

        taskApplicationService.completeTask(taskCompleteCommand);

        task = taskApplicationService.get(taskId1);
        assertThat(task.getTitle()).isEqualTo("task1");
        assertThat(task.getCompleted()).isTrue();
    }


    @Test
    void should_fuzzy_query_tasks_correctly() {
        TaskAddCommand taskAddCommand1 = new TaskAddCommand();
        taskAddCommand1.setTitle("task1");
        TaskAddCommand taskAddCommand2 = new TaskAddCommand();
        taskAddCommand2.setTitle("task2");
        taskApplicationService.addTask(taskAddCommand1);
        taskApplicationService.addTask(taskAddCommand2);

        TaskListQuery taskListQuery = new TaskListQuery();
        taskListQuery.setKeyword("task");

        List<TaskRepresentation> taskRepresentations = taskApplicationService.listQuery(taskListQuery);

        assertThat(taskRepresentations.size()).isEqualTo(2);
        assertThat(taskRepresentations.get(0).getTitle()).isEqualTo("task1");
        assertThat(taskRepresentations.get(1).getTitle()).isEqualTo("task2");
    }
}
package ddd.lab.domain;

import java.util.UUID;

import lombok.Data;

/**
 * @author ge
 * @date 2021/11/17
 */
@Data
public class Task {
    private UUID id;
    private String title;
    private Boolean completed;

    public Task(String title) {
        this(UUID.randomUUID(), title);
    }

    public Task(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public void complete() {
        this.setCompleted(true);
    }
}

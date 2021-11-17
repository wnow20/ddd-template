package ddd.lab.application;

import lombok.Data;

/**
 * @author ge
 * @date 2021/11/17
 */
@Data
public class TaskListQuery {
    private String keyword;
    private Boolean completed;
}

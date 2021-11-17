package ddd.lab.application;

import java.util.UUID;

import lombok.Data;

/**
 * @author ge
 * @date 2021/11/17
 */
@Data
public class TaskRepresentation {
    private UUID id;
    private String title;
    private Boolean completed;
}

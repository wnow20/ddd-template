package ddd.lab.application;

import java.util.UUID;

import lombok.Data;

/**
 * @author ge
 * @date 2021/11/17
 */
@Data
public class TaskCompleteCommand {
    private UUID id;
}

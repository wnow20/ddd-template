package ddd.lab.jpa;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author ge
 * @date 2021/11/17
 */
@Data
@Entity
@Table(name = "task")
public class TaskDataObject {
    @Id
    private UUID id;
    private String title;
    private Boolean completed;
    @Column(name = "create_time")
    private LocalDateTime createTime;
}

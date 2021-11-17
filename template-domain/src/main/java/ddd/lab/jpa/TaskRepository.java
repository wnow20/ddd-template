package ddd.lab.jpa;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ge
 * @date 2021/11/17
 */
@Repository
public interface TaskRepository extends PagingAndSortingRepository<TaskDataObject, UUID> {
    Iterable<TaskDataObject> findAllByTitleStartsWith(String title);

}

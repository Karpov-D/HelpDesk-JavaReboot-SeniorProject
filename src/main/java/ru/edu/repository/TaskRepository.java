package ru.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT task_id FROM task_user WHERE user_id = %:id%",
    nativeQuery = true)
    List<Long> getTasksId(@Param("id") Long id);

    @Query(value = "SELECT * FROM task WHERE id IN(%:id%)",
            nativeQuery = true)
    List<Task> getTasksListing(@Param("id") List<Long> id);



}

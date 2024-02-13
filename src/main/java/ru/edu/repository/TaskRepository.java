package ru.edu.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO task_user (task_id, user_id) VALUES (%:taskId%, %:userId%)",
            nativeQuery = true)
    void postTaskIdAndUserId(@Param("userId") Long userId,
                                   @Param("taskId") Long taskId);



}

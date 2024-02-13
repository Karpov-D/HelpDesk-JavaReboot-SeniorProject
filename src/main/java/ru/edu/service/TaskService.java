package ru.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.entity.Task;
import ru.edu.exception.ItemNotFoundException;
import ru.edu.repository.RoleRepository;
import ru.edu.repository.TaskRepository;
import ru.edu.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public List<Long> findAllTasksIdForUserOrSupport(Long id) {
        return taskRepository.getTasksId(id);
    }

    public List<Task> findAllTasksForUserOrSupport(List<Long> id) {
        return taskRepository.getTasksListing(id);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("User not found, id = " + id));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        findById(task.getId());
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        findById(id);
        taskRepository.deleteById(id);
    }

}

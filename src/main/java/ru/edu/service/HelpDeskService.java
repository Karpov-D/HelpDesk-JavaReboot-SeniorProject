package ru.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.entity.Task;
import ru.edu.repository.RoleRepository;
import ru.edu.repository.TaskRepository;
import ru.edu.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}

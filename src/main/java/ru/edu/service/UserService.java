package ru.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.entity.Task;
import ru.edu.entity.User;
import ru.edu.exception.ItemNotFoundException;
import ru.edu.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("User not found, id = " + id));
    }
}

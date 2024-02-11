package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.entity.Task;
import ru.edu.service.TaskService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/admin/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "user", description = "Admin API")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final TaskService service;

    @GetMapping
    @Operation(summary = "")
    public ResponseEntity<List<Task>> findAllTasks() {
        List<Task> tasks = service.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}

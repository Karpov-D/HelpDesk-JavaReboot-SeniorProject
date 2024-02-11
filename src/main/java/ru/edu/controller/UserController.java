package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.entity.Task;
import ru.edu.service.HelpDeskService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "user", description = "User API")
public class UserController  {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final HelpDeskService service;

    @GetMapping(value = "getAllTasks")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get all tasks")
    public ModelAndView findAllTasks() {
        List<Task> tasks = service.findAllTasks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", tasks);
        modelAndView.setViewName("getAllTasksPage");
        return modelAndView;
    }

    @GetMapping(value = "getMainPage")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "Get main page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

}

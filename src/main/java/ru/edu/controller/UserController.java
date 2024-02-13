package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.config.MyUserDetails;
import ru.edu.entity.Task;
import ru.edu.entity.User;
import ru.edu.exception.ItemNotFoundException;
import ru.edu.service.TaskService;
import ru.edu.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/users/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "user", description = "User API")
public class UserController  {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final TaskService service;
    private final UserService userService;

    public MyUserDetails foo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (MyUserDetails)authentication.getPrincipal();
        }
        return null;
    }


    @GetMapping(value = "getMainPage")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SUPPORT', 'ROLE_ADMIN')")
    @Operation(summary = "Get main page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping(value = "getAllTasks")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SUPPORT', 'ROLE_ADMIN')")
    @Operation(summary = "Get all tasks")
    public ModelAndView findAllTasks() {

        MyUserDetails res = foo();
        Long id = res.getId();

        List<Long> tasksId = service.findAllTasksIdForUserOrSupport(id);
        List<Task> tasks = service.findAllTasksForUserOrSupport(tasksId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", tasks);
        modelAndView.setViewName("getAllTasksPage");
        return modelAndView;
    }

    @GetMapping(value = "addTaskPage")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @Operation(summary = "Add task")
    public ModelAndView addTaskPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addTasksPage");
        return modelAndView;
    }


    @PostMapping("addTask")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ModelAndView postUser(@RequestParam("description") String description) {
        MyUserDetails res = foo();
        Long id = res.getId();

        ModelAndView modelAndView = new ModelAndView();
        Task task = new Task();
        task.setDescription(description);
        task.setStatus("CREATED");
        task = service.save(task);
        service.postTaskIdAndUserId(id, task.getId());

        modelAndView.setViewName("resultSuccess");
        return modelAndView;
    }

}

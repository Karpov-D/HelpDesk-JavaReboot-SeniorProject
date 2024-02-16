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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.config.MyUserDetails;
import ru.edu.entity.Task;
import ru.edu.exception.ItemNotFoundException;
import ru.edu.service.TaskService;

import java.util.List;

import static ru.edu.controller.DefaultMethods.foo;

@RestController
@RequestMapping(value = "api/v1/admin/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "admin", description = "Admin API")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final TaskService service;

    @GetMapping(value = "deleteTaskPage")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Add task")
    public ModelAndView deletePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteTask");
        return modelAndView;
    }

    @PostMapping("deleteTask")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView changeStatus(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Long taskId = Long.parseLong(id);
            Task task = service.findById(taskId);
            service.deleteById(task.getId());
            modelAndView.setViewName("resultSuccess");
            return modelAndView;
        }  catch (ItemNotFoundException | NumberFormatException e) {
            modelAndView.setViewName("resultError");
            return modelAndView;
        }
    }

    @GetMapping(value = "getAllTasks")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Get all tasks")
    public ModelAndView findAllTasks() {
        List<Task> tasks = service.findAllTasks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", tasks);
        modelAndView.setViewName("getAllTasksPage");
        return modelAndView;
    }

    @GetMapping(value = "changeStatusPage")
    @PreAuthorize("hasAnyRole('ROLE_SUPPORT', 'ROLE_ADMIN')")
    @Operation(summary = "Add task")
    public ModelAndView changeStatusPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("URL", "/api/v1/admin/changeStatus");
        modelAndView.setViewName("changeStatus");
        return modelAndView;
    }

    @PostMapping("changeStatus")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView changeStatus(@RequestParam("id") String id,
                                     @RequestParam("status") String status) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Long taskId = Long.parseLong(id);
            Task task = service.findById(taskId);
            task.setStatus(status);
            service.update(task);
            modelAndView.setViewName("resultSuccess");
            return modelAndView;
        } catch (ItemNotFoundException | NumberFormatException e) {
            modelAndView.setViewName("resultError");
            return modelAndView;
        }
    }
}

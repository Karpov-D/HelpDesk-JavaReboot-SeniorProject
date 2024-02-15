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
    public ModelAndView changeStatusPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteTask");
        return modelAndView;
    }


    @PostMapping("deleteTask")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView changeStatus(@RequestParam("id") Long id) {


        MyUserDetails res = foo();
        Long userId = res.getId();

        ModelAndView modelAndView = new ModelAndView();

        Task task = service.findById(id);


        service.deleteById(task.getId());

        modelAndView.setViewName("resultSuccess");
        return modelAndView;
    }
}

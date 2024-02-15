package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.config.MyUserDetails;
import ru.edu.entity.Task;
import ru.edu.exception.ItemNotFoundException;
import ru.edu.service.TaskService;
import ru.edu.service.UserService;

import java.util.List;

import static ru.edu.controller.DefaultMethods.foo;

@RestController
@RequestMapping(value = "api/v1/support/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "user", description = "Support API")
public class SupportController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final TaskService service;
    private final UserService userService;


    @GetMapping(value = "changeStatusPage")
    @PreAuthorize("hasAnyRole('ROLE_SUPPORT', 'ROLE_ADMIN')")
    @Operation(summary = "Add task")
    public ModelAndView changeStatusPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("changeStatus");
        return modelAndView;
    }


    @PostMapping("changeStatus")
    @PreAuthorize("hasAnyRole('ROLE_SUPPORT', 'ROLE_ADMIN')")
    public ModelAndView changeStatus(@RequestParam("id") String id,
                                     @RequestParam("status") String status) {


        MyUserDetails res = foo();
        Long userId = res.getId();
        List<Long> tasksId = service.findAllTasksIdForUserOrSupport(userId);

        ModelAndView modelAndView = new ModelAndView();

        try {
            Long taskId = Long.parseLong(id);
            if(tasksId.contains(taskId)) {
                Task task = service.findById(taskId);
                task.setStatus(status);
                service.update(task);
                modelAndView.setViewName("resultSuccess");
                return modelAndView;
            } else {
                modelAndView.setViewName("resultError");
                return modelAndView;
            }
        } catch (ItemNotFoundException | NumberFormatException e) {
            modelAndView.setViewName("resultError");
            return modelAndView;
        }

    }

}

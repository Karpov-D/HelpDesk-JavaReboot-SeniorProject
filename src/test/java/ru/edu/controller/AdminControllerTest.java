package ru.edu.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.entity.Task;
import ru.edu.entity.User;
import ru.edu.service.TaskService;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AdminController.class})
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;


    @Test
    void getAllTask() throws Exception {
        Set<User> users = new HashSet<>();
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new Task(1L,"IN WORK", "the Internet is not working", users),
                new Task(2L,"NEED INFO", "The computer is not working", users)
        ));
        when(service.findAllTasks()).thenReturn(tasks);
        mockMvc.perform(get("/api/v1/admin/getAllTasks"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    //@WithMockUser(username = "login_1", roles = "ROLE_ADMIN")
    void deleteTaskById() throws Exception {
        mockMvc.perform(post("/api/v1/admin/deleteTask")
                        .content("{\"id\": 1}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}

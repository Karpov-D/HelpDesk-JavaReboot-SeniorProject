package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.service.HelpDeskService;


@Controller
@RequestMapping(value = "/", consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor
@Tag(name = "welcome", description = "Welcome API")
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final HelpDeskService service;

    @GetMapping
    @Operation(summary = "Get welcome page")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("infoPage");
        return modelAndView;
    }
}

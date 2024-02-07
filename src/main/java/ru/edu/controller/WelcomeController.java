package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/", consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor
@Tag(name = "welcome", description = "Welcome API")
public class WelcomeController {

    @GetMapping
    @Operation(summary = "Get welcome page")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("infoPage");
        return modelAndView;
    }
}

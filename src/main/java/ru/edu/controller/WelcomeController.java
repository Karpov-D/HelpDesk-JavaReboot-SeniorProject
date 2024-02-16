package ru.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.config.MyUserDetails;

import static ru.edu.controller.DefaultMethods.foo;


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

    @GetMapping(value = "api/v1/getMainPage")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_SUPPORT', 'ROLE_ADMIN')")
    @Operation(summary = "Get main page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        MyUserDetails res = foo();
        String role = res.getRole().getName();
        if (role.equals("ROLE_USER")) {
            modelAndView.setViewName("mainUser");
            return modelAndView;
        } else if (role.equals("ROLE_SUPPORT")) {
            modelAndView.setViewName("mainSupport");
            return modelAndView;
        } else if (role.equals("ROLE_ADMIN")) {
            modelAndView.setViewName("mainAdmin");
            return modelAndView;
        } else
            modelAndView.setViewName("resultFail");
            return modelAndView;
    }

    @GetMapping(value = "fail")
    @Operation(summary = "Get fail page")
    public ModelAndView fail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resultFail");
        return modelAndView;
    }
}

package ru.edu.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.edu.config.MyUserDetails;

public class DefaultMethods {
    public static MyUserDetails foo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (MyUserDetails)authentication.getPrincipal();
        }
        return null;
    }
}

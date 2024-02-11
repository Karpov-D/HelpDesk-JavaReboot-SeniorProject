package ru.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.edu.config.MyUserDetails;
import ru.edu.entity.User;
import ru.edu.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user  = userRepository.findUserByLogin(login);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(login + " not found"));
    }
}

package com.example.demo.security;

import com.example.demo.dao.repository.UserRepository;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
package com.example.employee_api.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {

        return new User(
                username,
                "",
                new ArrayList<>()
        );
    }
}
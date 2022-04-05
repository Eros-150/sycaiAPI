package com.example.sycaiapi.service;

import org.springframework.stereotype.*;
import org.springframework.security.core.userdetails.*;
import java.util.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());//PASSWORD ENCRYPTED
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
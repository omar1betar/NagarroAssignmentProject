package com.betar.javaassignment.service;

import com.betar.javaassignment.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findByUsername(String username);
    User save(User user);
}

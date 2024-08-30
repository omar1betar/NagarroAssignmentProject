package com.betar.javaassignment.controller;

import com.betar.javaassignment.dtos.AuthenticationRequest;
import com.betar.javaassignment.dtos.AuthenticationResponse;
import com.betar.javaassignment.entity.User;
import com.betar.javaassignment.entity.UserSession;
import com.betar.javaassignment.service.impl.UserServiceImpl;
import com.betar.javaassignment.service.impl.UserSessionServiceImpl;
import com.betar.javaassignment.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;
    private UserSessionServiceImpl userSessionService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userService.findByUsername(userDetails.getUsername());
        UserSession userSession = userSessionService.findLastActiveSessionByUserId(user.getId());

        if(userSession != null ) {
            //check if the token is expired
            if(LocalDateTime.now().isAfter(userSession.getTokenExpiryDate())){
                userSessionService.deactivateSession(userSession);

            }else{
                throw new Exception("User already LoggedIn");
            }
        }
        final String jwt = jwtUtil.generateToken(userDetails);
        if (jwt != null) {
            userSessionService.createUserSession(jwt,user);
        }

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestParam("Authorization") String token) {
        userSessionService.deactivateSessionByToken(token);
        return ResponseEntity.ok("User logged out");

    }


}

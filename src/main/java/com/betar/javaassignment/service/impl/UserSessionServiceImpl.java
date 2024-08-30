package com.betar.javaassignment.service.impl;

import com.betar.javaassignment.entity.User;
import com.betar.javaassignment.entity.UserSession;
import com.betar.javaassignment.helpers.DateToLocalDateConverter;
import com.betar.javaassignment.repository.UserSessionRepository;
import com.betar.javaassignment.service.UserSessionService;
import com.betar.javaassignment.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UserSessionServiceImpl implements UserSessionService {

    private UserSessionRepository userSessionRepository;
    private JwtUtil jwtUtil;
    public UserSession createUserSession(String token, User user) {
        LocalDateTime tokenExoeration = DateToLocalDateConverter.convertToLocalDate(jwtUtil.extractExpiration(token));

        System.out.println("extractExpiration token: " + jwtUtil.extractExpiration(token));
        UserSession userSession = new UserSession();
        userSession.setUser(user);
        userSession.setToken(token);
        userSession.setTokenExpiryDate(tokenExoeration);
        return userSessionRepository.save(userSession);
    }
    public UserSession findLastActiveSessionByUserId(Long userId) {
        return userSessionRepository.findLastActiveSessionByUserId(userId);
    }
    public UserSession deactivateSession(UserSession userSession) {
        UserSession userSessionFromDb = userSessionRepository.findById(userSession.getId()).get();
        userSessionFromDb.setActive(false);
        return userSessionRepository.save(userSessionFromDb);

    }
    public UserSession deactivateSessionByToken(String token) {
        UserSession userSessionFromDb = userSessionRepository.findRecentActiveSessionByToken(token);
        userSessionFromDb.setActive(false);
        return userSessionRepository.save(userSessionFromDb);

    }

}

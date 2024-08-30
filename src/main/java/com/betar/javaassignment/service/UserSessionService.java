package com.betar.javaassignment.service;

import com.betar.javaassignment.entity.User;
import com.betar.javaassignment.entity.UserSession;

public interface UserSessionService {
    UserSession createUserSession(String token, User user);
    UserSession findLastActiveSessionByUserId(Long userId);
    UserSession deactivateSession(UserSession userSession);
    UserSession deactivateSessionByToken(String token);
}

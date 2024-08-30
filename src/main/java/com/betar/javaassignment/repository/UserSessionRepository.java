package com.betar.javaassignment.repository;

import com.betar.javaassignment.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {


    @Query("SELECT s FROM UserSession s WHERE s.user.id = :userId AND s.isActive = true ORDER BY s.id DESC")
    UserSession findLastActiveSessionByUserId(@Param("userId") Long userId);
    @Query("SELECT us FROM UserSession us WHERE us.token = :token AND us.isActive = true ORDER BY us.id DESC")
    UserSession findRecentActiveSessionByToken(@Param("token") String token);

}

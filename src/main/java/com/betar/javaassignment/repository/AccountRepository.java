package com.betar.javaassignment.repository;

import com.betar.javaassignment.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserId(Long userId);
}

package com.betar.javaassignment.entity;

import com.betar.javaassignment.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

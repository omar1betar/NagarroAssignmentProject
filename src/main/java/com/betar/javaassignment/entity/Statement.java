package com.betar.javaassignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "statements")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private LocalDate dateField;

    private BigDecimal amount;



}

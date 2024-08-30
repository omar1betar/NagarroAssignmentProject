package com.betar.javaassignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatementResponseDto {

    private Long user_id;
    private  String accountNumber;

    private LocalDate date;

    private BigDecimal amount;
}

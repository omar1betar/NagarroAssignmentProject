package com.betar.javaassignment.service;

import com.betar.javaassignment.dtos.StatementResponseDto;
import com.betar.javaassignment.entity.Account;
import com.betar.javaassignment.entity.Statement;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface StatmentService {

    List<StatementResponseDto> getStatementsByParams(@Param("accountId") Long accountId,
                                                     @Param("fromDate") LocalDate fromDate,
                                                     @Param("toDate") LocalDate toDate,
                                                     @Param("fromAmount") BigDecimal fromAmount,
                                                     @Param("toAmount") BigDecimal toAmount
    );
    public List<StatementResponseDto> getLastThreeMonths(@Param("accountId") Long accountId);

    List<StatementResponseDto> returnListOfStatements(List<Statement> statements, Account account);
    List<StatementResponseDto> getUserStatement(String username);
}

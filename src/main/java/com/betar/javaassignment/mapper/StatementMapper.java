package com.betar.javaassignment.mapper;

import com.betar.javaassignment.dtos.StatementResponseDto;
import com.betar.javaassignment.entity.Account;
import com.betar.javaassignment.entity.Statement;

public class StatementMapper {

    public static StatementResponseDto ToStatementResponseDto(Statement statement, Account account) {

        return new StatementResponseDto(
                account.getUser().getId(),
            account.getAccountNumber(),
            statement.getDateField(),
            statement.getAmount()
        );
    }
}

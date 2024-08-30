package com.betar.javaassignment.service.impl;


import com.betar.javaassignment.dtos.StatementResponseDto;
import com.betar.javaassignment.entity.Account;
import com.betar.javaassignment.entity.Statement;
import com.betar.javaassignment.mapper.StatementMapper;
import com.betar.javaassignment.repository.AccountRepository;
import com.betar.javaassignment.repository.StatementRepository;
import com.betar.javaassignment.service.StatmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatementServiceImpl  implements StatmentService {

    private StatementRepository statementRepository;
    private AccountRepository accountRepository;
    private UserServiceImpl userService;


    public List<StatementResponseDto> getStatementsByParams(@Param("accountId") Long accountId,
                                                                       @Param("fromDate") LocalDate fromDate,
                                                                       @Param("toDate") LocalDate toDate,
                                                                       @Param("fromAmount") BigDecimal fromAmount,
                                                                       @Param("toAmount") BigDecimal toAmount
                                                                       ) {

        // Fetch statements from the repository
        List<Statement> statements = statementRepository.
                getStatements(accountId,
                        fromDate,toDate,fromAmount,
                        toAmount);

        // Retrieve the account
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account not found with ID: " + accountId));
        account.setAccountNumber(new BCryptPasswordEncoder().encode(account.getAccountNumber()));

        return returnListOfStatements(statements,account);
    }

    public List<StatementResponseDto> getLastThreeMonths(@Param("accountId") Long accountId){
         var threeMonthsAgo = LocalDate.now().minusMonths(3);

        List<Statement> statements = statementRepository.findStatementThreeMonthsAgo
                (accountId,threeMonthsAgo);

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account not found with ID: " + accountId));

        account.setAccountNumber(new BCryptPasswordEncoder().encode(account.getAccountNumber()));


        return returnListOfStatements(statements,account);

    }



    public List<StatementResponseDto> returnListOfStatements(List<Statement> statements, Account account) {
        return statements.stream()
                .map(statement -> StatementMapper.ToStatementResponseDto(statement, account))
                .collect(Collectors.toList());
    }


    public List<StatementResponseDto> getUserStatement(String username) {
        var user = userService.findByUsername(username); //get user details

        Account account = accountRepository.findByUserId(user.getId());
        if (account == null) {
            throw new NoSuchElementException("Account not found with ID: " + account.getId());
        }
       return getLastThreeMonths(account.getId());

    }

}

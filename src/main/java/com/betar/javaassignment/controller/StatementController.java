package com.betar.javaassignment.controller;

import com.betar.javaassignment.dtos.StatementResponseDto;
import com.betar.javaassignment.service.impl.StatementServiceImpl;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class StatementController {

    private StatementServiceImpl statementServiceImpl;

    @GetMapping("/getaccountstatement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatementResponseDto>> getStatement(
            @RequestParam(name = "account_id", defaultValue = "") @Positive Long account_id,
            @RequestParam(name = "from_date", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(name = "to_date", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(name = "from_amount", defaultValue = "") @Positive BigDecimal fromAmount,
            @RequestParam(name = "to_amount", defaultValue = "") @Positive BigDecimal toAmount
    ){

        List<StatementResponseDto> returnList = new ArrayList<>();
        if(fromDate==null || toDate==null || fromAmount==null || toAmount==null) {
            returnList = statementServiceImpl.getLastThreeMonths(account_id);
            return new ResponseEntity<>(returnList, HttpStatus.OK);
        }
        returnList = statementServiceImpl.getStatementsByParams(account_id,fromDate,toDate,fromAmount,toAmount);
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getuserstatement")
    public ResponseEntity<List<StatementResponseDto>> getUserStatement(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"))) {
            // User has the "ROLE_ADMIN" authority
            System.out.println(authentication.getAuthorities());

        }

        var username = authentication.getName();//get username
        var statements = statementServiceImpl.getUserStatement(username); //get user details
        return new ResponseEntity<>(statements, HttpStatus.OK);
    }
}

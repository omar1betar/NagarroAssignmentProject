package com.betar.javaassignment.repository;

import com.betar.javaassignment.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, Long> {

    @Query("SELECT s FROM Statement s WHERE s.account.id = :accountId " +
            "AND s.dateField BETWEEN :fromDate AND :toDate " +
            "AND s.amount BETWEEN :fromAmount AND :toAmount " )
    List<Statement> getStatements(@Param("accountId") Long accountId,
                                                      @Param("fromDate") LocalDate fromDate,
                                                      @Param("toDate") LocalDate toDate,
                                                      @Param("fromAmount") BigDecimal fromAmount,
                                                      @Param("toAmount") BigDecimal toAmount
                                                      );

    @Query("SELECT s FROM Statement s WHERE s.account.id = :accountId AND s.dateField >= :threeMonthsAgo")
    List<Statement> findStatementThreeMonthsAgo(@Param("accountId") Long accountId,
                                                @Param("threeMonthsAgo") LocalDate threeMonthsAgo);

}

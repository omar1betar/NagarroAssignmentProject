package com.betar.javaassignment.repository;

import com.betar.javaassignment.entity.Account;
import com.betar.javaassignment.entity.Statement;
import com.betar.javaassignment.entity.User;
import com.betar.javaassignment.enums.AccountType;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StatementRepositoryTest {

    @Autowired
    private StatementRepository statementRepoository;
//    @Autowired
//    private AccountRepository accountRepoository;
//    @Autowired
//    private UserRepository userRepoository;

    @Test
    public void statementrepository_getStatements_returnsAllStatements() {

        //arrange
        User user = new User().builder().username("username").password("password").build();

        Account account = new Account().builder().accountType(AccountType.valueOf("Saving")).accountNumber(String.valueOf(23456789)).user(user).build();


        Statement statement = new Statement().builder()
                .dateField(LocalDate.of(2024,05,05))
                .amount(BigDecimal.valueOf(345654.0))
                .account(account).build();

        //act
//        userRepoository.save(user);
//        accountRepoository.save(account);
        statementRepoository.save(statement);
        List<Statement> statements = statementRepoository.getStatements(account.getId(),
                LocalDate.of(2024,05,04),
                LocalDate.of(2024,05,06),
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(385654.0)
                );
        //assert
        Assertions.assertNotNull(statements);
        Assertions.assertEquals(1, statements.size());
        Assertions.assertEquals(statement.getAmount(), statements.get(0).getAmount());
        Assertions.assertEquals(statement.getDateField(), statements.get(0).getDateField());
        Assertions.assertEquals(statement.getAccount().getAccountNumber(), statements.get(0).getAccount().getAccountNumber());

    }

}

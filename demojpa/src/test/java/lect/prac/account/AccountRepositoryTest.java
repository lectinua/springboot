package lect.prac.account;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
    
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {
        try(Connection conn = dataSource.getConnection()) {
            Account acc = new Account();
            acc.setUsername("lect");
            acc.setPassword("prac");

            Account nacc = accountRepository.save(acc);
            assertThat(nacc, is(notNullValue()));

            /*{
                Account find = accountRepository.findByUsername(nacc.getUsername());
                assertThat(find, is(notNullValue()));

                Account notFound = accountRepository.findByUsername("none");
                assertThat(notFound, is(nullValue()));
            }*/

            {
                Optional<Account> find = accountRepository.findByUsername(nacc.getUsername());
                assertThat(find.isPresent(), Is.is(true));

                Optional<Account> notFound = accountRepository.findByUsername("none");
                assertThat(notFound, Is.is(Optional.empty()));
                assertThat(notFound.isPresent(), Is.is(false));
            }
        }
    }
}

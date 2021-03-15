package lect.prac.demo;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PgSQLRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            // class com.zaxxer.hikari.pool.HikariProxyDatabaseMetaData
            System.out.println(">>> " + conn.getMetaData().getClass());
            // PostgreSQL JDBC Driver
            System.out.println(">>> " + conn.getMetaData().getDriverName());
            conn.getMetaData().getURL();
            conn.getMetaData().getUserName();
    
            Statement st = conn.createStatement();
            st.executeUpdate("DROP TABLE TB_USER");
            st.executeUpdate("CREATE TABLE TB_USER (ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY (id))");
            st.executeUpdate("INSERT INTO TB_USER VALUES (1, 'test')");
            conn.commit();
        }

        // AUTOCOMMIT=FALSE
        // jdbcTemplate.execute("INSERT INTO TB_USER VALUES (2, '22')");
        // jdbcTemplate.execute("UPDATE TB_USER SET NAME = 'TT' WHERE ID = 3");
    }
}

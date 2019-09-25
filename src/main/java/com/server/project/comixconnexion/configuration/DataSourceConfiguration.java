package com.server.project.comixconnexion.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.activation.DataSource;

@Configuration
@Profile("local")
public class DataSourceConfiguration {

    private static final String USER_NAME = System.getenv("DB_UN");
    private static final String PASSWORD = System.getenv("DB_PW");
    private static final String URL = System.getenv("DB_URL");

    @Bean
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(USER_NAME);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");

        return (DataSource) new HikariDataSource(hikariConfig);

    }
}

package com.project;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(
            @Value("${MYSQLHOST}") String mysqlHost,
            @Value("${MYSQLPORT}") String mysqlPort,
            @Value("${MYSQL_DATABASE}") String mysqlDatabase,
            @Value("${MYSQLUSER}") String mysqlUser,
            @Value("${MYSQLPASSWORD}") String mysqlPassword) {

        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC",
                mysqlHost, mysqlPort, mysqlDatabase);

        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(url)
                .username(mysqlUser)
                .password(mysqlPassword)
                .build();
    }


}
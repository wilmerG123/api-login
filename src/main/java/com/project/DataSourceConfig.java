package com.project;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {


    @Bean
    public DataSource getDataSource(
            @Value("${MYSQL_HOST}") String mysqlHost,
            @Value("${MYSQL_PORT}") String mysqlPort,
            @Value("${MYSQL_DATABASE}") String mysqlDatabase,
            @Value("${MYSQL_USER}") String mysqlUser,
            @Value("${MYSQL_PASSWORD}") String mysqlPassword) {

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
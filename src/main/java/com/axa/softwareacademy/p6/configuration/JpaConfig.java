package com.axa.softwareacademy.p6.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

public class JpaConfig {
    @Bean(name = "mySqlDataSource")
    @Primary
    public DataSource mySqlDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/pmb");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("rootroot");
        return dataSourceBuilder.build();
    }
}


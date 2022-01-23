package com.nannayeong.awd.config.infra;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

  @Primary
  @Bean(value = "mariaDataSource")
  @ConfigurationProperties(value = "spring.datasource.hikari.maria")
  public DataSource MariaDataSource() {

    log.info("Create MariaDataSource Bean");

    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }
}

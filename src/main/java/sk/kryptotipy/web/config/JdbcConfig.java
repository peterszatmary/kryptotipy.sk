package sk.kryptotipy.web.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sk.kryptotipy.web.AppProperties;

import javax.sql.DataSource;

@AllArgsConstructor
@Configuration
public class JdbcConfig {

    private final AppProperties properties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDbDriver());
        dataSource.setUrl(properties.getDbUrl());
        dataSource.setUsername(properties.getDbUsername());
        dataSource.setPassword(properties.getDbPassword());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}


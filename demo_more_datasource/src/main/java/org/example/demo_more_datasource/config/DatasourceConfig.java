package org.example.demo_more_datasource.config;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.sql.DataSource;

/**
 * *@ClassName mainDatasource
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:06
 * *Version 1.0
 **/
@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource ucDataSource() {
        return DataSourceBuilder.create().build();
    }

    // JdbcTemplate
    @Bean(name = "mainJdbc")
    @Primary
    public JdbcTemplate mainJdbc(@Qualifier("mainDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "ucJdbc")
    public JdbcTemplate ucJdbc(@Qualifier("ucDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // 分布式事务 (Seata 需要 DataSourceProxy)
    @Primary
    @Bean("mainDataSourceProxy")
    public DataSourceProxy mainDataSourceProxy(@Qualifier("mainDataSource") DataSource ds) {
        return new DataSourceProxy(ds);
    }

    @Bean("ucDataSourceProxy")
    public DataSourceProxy ucDataSourceProxy(@Qualifier("ucDataSource") DataSource ds) {
        return new DataSourceProxy(ds);
    }

    // TaskScheduler
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(4);
        return scheduler;
    }
}


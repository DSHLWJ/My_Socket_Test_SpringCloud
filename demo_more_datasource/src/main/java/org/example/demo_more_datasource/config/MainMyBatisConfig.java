package org.example.demo_more_datasource.config;

import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * *@ClassName MainMyBatisConfig
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:07
 * *Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "org.example.demo_more_datasource.mapper.main", sqlSessionFactoryRef = "mainSqlSessionFactory")
public class MainMyBatisConfig {

    @Bean
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSourceProxy") DataSourceProxy ds) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate mainSqlSessionTemplate(@Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}


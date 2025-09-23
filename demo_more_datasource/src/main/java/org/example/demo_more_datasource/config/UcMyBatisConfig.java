package org.example.demo_more_datasource.config;

import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * *@ClassName ucSqlSessionFactory
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:07
 * *Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "org.example.demo_more_datasource.mapper.uc", sqlSessionFactoryRef = "ucSqlSessionFactory")
public class UcMyBatisConfig {

    @Bean
    public SqlSessionFactory ucSqlSessionFactory(@Qualifier("ucDataSourceProxy") DataSourceProxy ds) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate ucSqlSessionTemplate(@Qualifier("ucSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

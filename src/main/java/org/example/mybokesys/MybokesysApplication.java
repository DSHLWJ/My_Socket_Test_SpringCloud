package org.example.mybokesys;

import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRyFeignClients
@SpringBootApplication
@MapperScan("org.example.mybokesys.mapper")
public class MybokesysApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybokesysApplication.class, args);
    }

}

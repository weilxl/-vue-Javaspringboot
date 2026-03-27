package com.efficiency;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.efficiency.mapper")
public class EfficiencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(EfficiencyApplication.class, args);
    }
}

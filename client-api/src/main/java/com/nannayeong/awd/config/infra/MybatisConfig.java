package com.nannayeong.awd.config.infra;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
    basePackages = "com.nannayeong.awd.mapper",
    basePackageClasses = Mapper.class
)
public class MybatisConfig {
}

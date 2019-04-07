package com.remango.blog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zhengli
 * @since 2019/03/07
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.remango.blog.dao")
public class MybatisConfig {
}

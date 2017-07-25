package com.github.vp.examples.spring.dsl.config;

import com.github.vp.examples.spring.dsl.bean.DefaultConstructorPojoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vimalpar on 30/06/17.
 */
@Configuration
public class SimpleBeanConfiguration {
    @Bean
    public DefaultConstructorPojoBean defaultConstructorPojoBean() {
        return new DefaultConstructorPojoBean();
    }
}

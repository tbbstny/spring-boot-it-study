package com.ttt.example.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuilderConfig
{
    @Bean
    public TestContactBuilder testContactBuilder() {
        TestContactBuilder builder = new TestContactBuilder();
        return builder;
    }

}

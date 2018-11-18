package com.ttt.example.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Spring Application Config file.
 */
@Configuration
@EnableJpaRepositories
@EnableSpringDataWebSupport
public class ApplicationConfig
{
}

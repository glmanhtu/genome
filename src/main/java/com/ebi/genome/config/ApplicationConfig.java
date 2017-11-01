package com.ebi.genome.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Override
    public String toString() {
        return "{ApplicationConfig}";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

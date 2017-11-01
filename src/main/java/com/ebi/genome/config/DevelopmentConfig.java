package com.ebi.genome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@Profile("dev")
@PropertySource("classpath:profile/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public String toString() {
        return "{DevelopmentConfig}";
    }
}

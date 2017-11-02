package com.ebi.genome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configuration
@Profile("prod")
@PropertySource("classpath:profile/application-prod.properties")
public class ProductionConfig {
}
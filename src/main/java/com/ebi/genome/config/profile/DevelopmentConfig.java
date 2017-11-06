package com.ebi.genome.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Profile("dev")
@PropertySource("classpath:profile/application-dev.properties")
public class DevelopmentConfig {
}

package com.ebi.genome.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("integration-test")
@PropertySource("classpath:profile/application-integration-test.properties")
public class IntegrationTestConfig {
}

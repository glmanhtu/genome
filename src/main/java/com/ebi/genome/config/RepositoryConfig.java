package com.ebi.genome.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by optimize on 4/17/17.
 */
@Configuration
@EntityScan(basePackages = {"com.ebi.genome.persistence.domain"})
@EnableJpaRepositories(basePackages = {"com.ebi.genome.persistence.repository"})
@EnableTransactionManagement
public class RepositoryConfig {
}

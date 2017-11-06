package com.ebi.genome.config.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Profile("docker")
@PropertySource("classpath:profile/application-docker.properties")
public class DockerConfig {
}

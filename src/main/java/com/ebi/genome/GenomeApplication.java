package com.ebi.genome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.SocketUtils;

@SpringBootApplication
public class GenomeApplication {

    @Bean
    public Integer port() {
        return SocketUtils.findAvailableTcpPort();
    }

    public static void main(String[] args) {
        SpringApplication.run(GenomeApplication.class, args);
    }

    @Override
    public String toString() {
        return "{GenomeApplication}";
    }

}

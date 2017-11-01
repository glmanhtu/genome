package com.ebi.genome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenomeApplication.class, args);
    }

    @Override
    public String toString() {
        return "{GenomeApplication}";
    }

}

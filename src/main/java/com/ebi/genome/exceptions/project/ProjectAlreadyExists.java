package com.ebi.genome.exceptions.project;

import com.ebi.genome.exceptions.GenomeException;
import org.springframework.http.HttpStatus;

public class ProjectAlreadyExists extends GenomeException {

    public ProjectAlreadyExists() {
        super("Project already exists", HttpStatus.BAD_REQUEST);
    }
}

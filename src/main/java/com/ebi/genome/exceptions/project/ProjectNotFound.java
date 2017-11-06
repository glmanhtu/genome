package com.ebi.genome.exceptions.project;

import com.ebi.genome.exceptions.GenomeException;
import org.springframework.http.HttpStatus;

public class ProjectNotFound extends GenomeException {

    public ProjectNotFound() {
        super("Project not found", HttpStatus.NOT_FOUND);
    }
}

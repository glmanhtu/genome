package com.ebi.genome.exceptions.project;

import com.ebi.genome.exceptions.GenomeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectAlreadyExists extends GenomeException {

    public ProjectAlreadyExists() {
        super("Project already exists");
    }
}

package com.ebi.genome.exceptions.project;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProjectNotFound extends RuntimeException {
    public ProjectNotFound() {
        super("Project not found");
    }
}

package com.ebi.genome.exceptions.taxonomy;

import com.ebi.genome.exceptions.GenomeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaxonomyNotExist extends GenomeException {
    public TaxonomyNotExist() {
        super("Taxonomy not exists");
    }
}

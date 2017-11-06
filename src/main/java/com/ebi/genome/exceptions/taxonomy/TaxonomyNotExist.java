package com.ebi.genome.exceptions.taxonomy;

import com.ebi.genome.exceptions.GenomeException;
import org.springframework.http.HttpStatus;

public class TaxonomyNotExist extends GenomeException {
    public TaxonomyNotExist() {
        super("Taxonomy not exists", HttpStatus.BAD_REQUEST);
    }
}

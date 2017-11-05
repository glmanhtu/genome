package com.ebi.genome.service;

import com.ebi.genome.exceptions.taxonomy.TaxonomyNotExist;
import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.repository.TaxonomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxonomyService {

    @Autowired
    private TaxonomyRepository taxonomyRepository;

    public Taxonomy getTaxonomy(int taxonomiesId) {
        Taxonomy taxonomy = taxonomyRepository.findOne(taxonomiesId);
        if (taxonomy == null) {
            throw new TaxonomyNotExist();
        }
        return taxonomy;
    }

    public List<Taxonomy> getTaxonomies() {
        return taxonomyRepository.findAll();
    }
}

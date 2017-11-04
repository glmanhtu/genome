package com.ebi.genome.service;

import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.repository.TaxonomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxonomiesService {

    @Autowired
    private TaxonomyRepository taxonomyRepository;

    public Taxonomy getTaxonomy(int taxonomiesId) {
        return taxonomyRepository.findOne(taxonomiesId);
    }

    public List<Taxonomy> getTaxonomies() {
        return taxonomyRepository.findAll();
    }
}

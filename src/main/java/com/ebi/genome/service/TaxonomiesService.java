package com.ebi.genome.service;

import com.ebi.genome.persistence.domain.Taxonomies;
import com.ebi.genome.persistence.repository.TaxonomiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxonomiesService {

    @Autowired
    private TaxonomiesRepository taxonomiesRepository;

    public Taxonomies getTaxonomies(int taxonomiesId) {
        return taxonomiesRepository.findOne(taxonomiesId);
    }
}

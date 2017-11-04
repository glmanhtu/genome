package com.ebi.genome.persistence.repository;

import com.ebi.genome.persistence.domain.Taxonomy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaxonomyRepository extends CrudRepository<Taxonomy, Integer> {
    List<Taxonomy> findAll();
}

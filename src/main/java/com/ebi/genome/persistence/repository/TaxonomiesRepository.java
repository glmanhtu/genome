package com.ebi.genome.persistence.repository;

import com.ebi.genome.persistence.domain.Taxonomies;
import org.springframework.data.repository.CrudRepository;

public interface TaxonomiesRepository extends CrudRepository<Taxonomies, Integer> {
}

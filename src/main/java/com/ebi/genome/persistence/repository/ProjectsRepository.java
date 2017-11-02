package com.ebi.genome.persistence.repository;

import com.ebi.genome.persistence.domain.Projects;
import com.ebi.genome.persistence.domain.Taxonomies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectsRepository extends CrudRepository<Projects, String> {
    Page<Projects> findAll(Pageable pageable);
    Page<Projects> findByTaxonomiesIn(List<Taxonomies> taxonomies, Pageable pageable);
    Page<Projects> findByStudyTypeContaining(String studyType, Pageable pageable);
}

package com.ebi.genome.persistence.repository;

import com.ebi.genome.persistence.domain.Project;
import com.ebi.genome.persistence.domain.Taxonomy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, String> {
    Page<Project> findAll(Pageable pageable);
    Page<Project> findByTaxonomyIn(List<Taxonomy> taxonomies, Pageable pageable);
    Page<Project> findByStudyTypeContaining(String studyType, Pageable pageable);
}

package com.ebi.genome.service;

import com.ebi.genome.exceptions.project.ProjectNotFound;
import com.ebi.genome.persistence.domain.Projects;
import com.ebi.genome.persistence.domain.Taxonomies;
import com.ebi.genome.persistence.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    public Page<Projects> getProjects(Pageable pageable) {
        return projectsRepository.findAll(pageable);
    }

    public Page<Projects> getProjectsByTaxonomies(List<Taxonomies> taxonomies, Pageable pageable) {
        return projectsRepository.findByTaxonomiesIn(taxonomies, pageable);
    }

    public Page<Projects> getProjectsByStudyType(String studyType, Pageable pageable) {
        return projectsRepository.findByStudyTypeContaining(studyType, pageable);
    }

    public Projects getProject(String projectId) {
        Projects projects = projectsRepository.findOne(projectId);
        if (projects == null) {
            throw new ProjectNotFound();
        }
        return projects;
    }
}

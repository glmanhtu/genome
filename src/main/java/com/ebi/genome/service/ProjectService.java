package com.ebi.genome.service;

import com.ebi.genome.exceptions.project.ProjectAlreadyExists;
import com.ebi.genome.exceptions.project.ProjectNotFound;
import com.ebi.genome.persistence.domain.Project;
import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Page<Project> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Page<Project> getProjectsByTaxonomies(List<Taxonomy> taxonomies, Pageable pageable) {
        return projectRepository.findByTaxonomyIn(taxonomies, pageable);
    }

    public Page<Project> getProjectsByStudyType(String studyType, Pageable pageable) {
        return projectRepository.findByStudyTypeContaining(studyType, pageable);
    }

    public Project getProject(String projectId) {
        Project project = projectRepository.findOne(projectId);
        if (project == null) {
            throw new ProjectNotFound();
        }
        return project;
    }

    public boolean isProjectExists(String projectId) {
        Project project = projectRepository.findOne(projectId);
        return project != null;
    }

    public Project createProject(Project project) {
        if (isProjectExists(project.getProjectId())) {
            throw new ProjectAlreadyExists();
        }
        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        if (isProjectExists(project.getProjectId())) {
            return projectRepository.save(project);
        }
        throw new ProjectNotFound();
    }

    public void deleteProject(Project project) {
        if (isProjectExists(project.getProjectId())) {
            projectRepository.delete(project);
        }
        throw new ProjectNotFound();
    }
}

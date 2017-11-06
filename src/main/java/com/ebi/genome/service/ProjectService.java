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

    /**
     * Get projects
     * Supported Paging, Order
     * @param pageable
     * @return Page<Project>
     */
    public Page<Project> getProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    /**
     * Get projects by list of Taxonomy
     * Support Paging, Order
     * @param taxonomies
     * @param pageable
     * @return Page<Project>
     */
    public Page<Project> getProjectsByTaxonomies(List<Taxonomy> taxonomies, Pageable pageable) {
        return projectRepository.findByTaxonomyIn(taxonomies, pageable);
    }

    /**
     * Get projects by study type
     * Support Paging, Order
     * @param studyType
     * @param pageable
     * @return Page<Project>
     */
    public Page<Project> getProjectsByStudyType(String studyType, Pageable pageable) {
        return projectRepository.findByStudyTypeContaining(studyType, pageable);
    }

    /**
     * Get project by ProjectID
     * @param projectId
     * @throws ProjectNotFound when unable to find project by given id
     * @return Project
     */
    public Project getProject(String projectId) {
        Project project = projectRepository.findOne(projectId);
        if (project == null) {
            throw new ProjectNotFound();
        }
        return project;
    }

    /**
     * Check if project exist or not
     * @param projectId
     * @return true if project exist, otherwise false
     */
    public boolean isProjectExists(String projectId) {
        Project project = projectRepository.findOne(projectId);
        return project != null;
    }

    /**
     * Create new project
     * @param project
     * @throws ProjectAlreadyExists if project already exist on db
     * @return Project after saved
     */
    public Project createProject(Project project) {
        if (isProjectExists(project.getProjectId())) {
            throw new ProjectAlreadyExists();
        }
        return projectRepository.save(project);
    }

    /**
     * Update project details
     * @param project
     * @throws ProjectNotFound if can't find project with given project.projectId
     * @return Project after updated
     */
    public Project updateProject(Project project) {
        if (isProjectExists(project.getProjectId())) {
            return projectRepository.save(project);
        }
        throw new ProjectNotFound();
    }

    /**
     * Delete project
     * @throws ProjectNotFound if can't find project with given project.projectId
     * @param project
     */
    public void deleteProject(Project project) {
        if (isProjectExists(project.getProjectId())) {
            projectRepository.delete(project);
        }
        throw new ProjectNotFound();
    }
}

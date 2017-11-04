package com.ebi.genome.restapi;

import com.ebi.genome.persistence.domain.Project;
import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.dto.ProjectDTO;
import com.ebi.genome.service.ProjectService;
import com.ebi.genome.service.TaxonomiesService;
import com.ebi.genome.utils.DefaultResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/studies")
public class StudiesHandler {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaxonomiesService taxonomiesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAllStudies(Pageable pageRequest) {
        Page<Project> projects = projectService.getProjects(pageRequest);
        Page<ProjectDTO> projectsDTOs = projects.map(project1 -> {
            ProjectDTO projectDTO = convertToDTO(project1);
            projectDTO.setDescription("");
            return projectDTO;
        });
        return DefaultResponse.success(projectsDTOs);
    }

    @GetMapping(params = {"taxonomyId"})
    public ResponseEntity<?> findAllStudiesByTaxonomyId(
            @RequestParam("taxonomyId") int taxonomyId, Pageable pageRequest) {
        Taxonomy taxonomy = taxonomiesService.getTaxonomy(taxonomyId);
        Page<Project> projects = projectService.getProjectsByTaxonomies(
                Collections.singletonList(taxonomy), pageRequest);
        Page<ProjectDTO> projectsDTOs = projects.map(project1 -> {
            ProjectDTO projectDTO = convertToDTO(project1);
            projectDTO.setDescription("");
            return projectDTO;
        });
        return DefaultResponse.success(projectsDTOs);
    }

    @GetMapping(params = {"studyType"})
    public ResponseEntity<?> findAllStudiesByStudyType(
            @RequestParam("studyType") String studyType, Pageable pageRequest) {
        Page<Project> projects = projectService.getProjectsByStudyType(studyType, pageRequest);
        Page<ProjectDTO> projectsDTOs = projects.map(project1 -> {
            ProjectDTO projectDTO = convertToDTO(project1);
            projectDTO.setDescription("");
            return projectDTO;
        });
        return DefaultResponse.success(projectsDTOs);
    }

    @GetMapping(value = "/{projectsId}")
    public ResponseEntity<?> getStudy(@PathVariable("projectsId") String projectsId) {
        Project project = projectService.getProject(projectsId);
        ProjectDTO projectDTO = convertToDTO(project);
        return DefaultResponse.success(projectDTO);
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
        projectDTO.setTaxonomyId(project.getTaxonomy().getTaxonomyId());
        return projectDTO;
    }
}

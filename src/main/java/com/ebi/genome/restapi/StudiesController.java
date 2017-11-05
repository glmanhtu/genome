package com.ebi.genome.restapi;

import com.ebi.genome.persistence.domain.Project;
import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.dto.ProjectDTO;
import com.ebi.genome.service.ProjectService;
import com.ebi.genome.service.TaxonomyService;
import com.ebi.genome.utils.DefaultResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/studies")
public class StudiesController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaxonomyService taxonomyService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudiesController.class);

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
        Taxonomy taxonomy = taxonomyService.getTaxonomy(taxonomyId);
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

    @GetMapping(value = "/{projectId}")
    public ResponseEntity<?> getStudy(@PathVariable("projectId") String projectId) {
        Project project = projectService.getProject(projectId);
        ProjectDTO projectDTO = convertToDTO(project);
        return DefaultResponse.success(projectDTO);
    }

    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<?> deleteStudy(@PathVariable("projectId") String projectId) {
        Project project = projectService.getProject(projectId);
        projectService.deleteProject(project);
        return DefaultResponse.success();
    }

    @PutMapping()
    public ResponseEntity<?> updateStudy(@Valid @RequestBody ProjectDTO projectDTO) {
        LOGGER.debug("Updating project {}", projectDTO);
        LOGGER.debug("Step 1/4");
        Project project = projectService.getProject(projectDTO.getProjectId());
        LOGGER.debug("Step 2/4");
        Taxonomy taxonomy = taxonomyService.getTaxonomy(projectDTO.getTaxonomyId());
        LOGGER.debug("Step 3/4");
        project.setTaxonomy(taxonomy);
        project.setCenterName(projectDTO.getCenterName());
        project.setDescription(projectDTO.getDescription());
        project.setEvaCenterName(projectDTO.getEvaCenterName());
        project.setSourceType(projectDTO.getSourceType());
        project.setStudyType(projectDTO.getStudyType());
        project.setTitle(projectDTO.getTitle());
        LOGGER.debug("Step 4/4");
        project = projectService.updateProject(project);
        LOGGER.debug("Updated project");
        return DefaultResponse.success(project);
    }

    @PostMapping
    public ResponseEntity<?> createStudy(@Valid @RequestBody ProjectDTO projectDTO) {
        LOGGER.debug("Creating new project {}", projectDTO);
        LOGGER.debug("Step 1/3");

        Project project = modelMapper.map(projectDTO, Project.class);

        LOGGER.debug("Step 2/3");
        Taxonomy taxonomy = taxonomyService.getTaxonomy(projectDTO.getTaxonomyId());
        project.setTaxonomy(taxonomy);

        LOGGER.debug("Step 3/3");
        project = projectService.createProject(project);

        LOGGER.debug("Created new project");
        return DefaultResponse.success(project);
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
        projectDTO.setTaxonomyId(project.getTaxonomy().getTaxonomyId());
        return projectDTO;
    }
}

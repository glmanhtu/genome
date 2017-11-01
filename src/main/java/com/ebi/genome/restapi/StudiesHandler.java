package com.ebi.genome.restapi;

import com.ebi.genome.persistence.domain.Projects;
import com.ebi.genome.persistence.domain.Taxonomies;
import com.ebi.genome.persistence.dto.ProjectsDTO;
import com.ebi.genome.service.ProjectsService;
import com.ebi.genome.service.TaxonomiesService;
import com.ebi.genome.utils.DefaultResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/studies")
public class StudiesHandler {

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private TaxonomiesService taxonomiesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<?> findAllStudies(Pageable pageRequest) {
        Page<Projects> projects = projectsService.getProjects(pageRequest);
        Page<ProjectsDTO> projectsDTOs = projects.map(projects1 -> {
            ProjectsDTO projectsDTO = convertToDTO(projects1);
            projectsDTO.setDescription("");
            return projectsDTO;
        });
        return DefaultResponse.success(projectsDTOs);
    }

    @GetMapping(params = {"taxonomyId"})
    public ResponseEntity<?> findAllStudiesByTaxonomyId(
            @RequestParam("taxonomyId") int taxonomyId, Pageable pageRequest) {
        Taxonomies taxonomies = taxonomiesService.getTaxonomies(taxonomyId);
        Page<Projects> projects = projectsService.getProjectsByTaxonomies(
                Collections.singletonList(taxonomies), pageRequest);
        Page<ProjectsDTO> projectsDTOs = projects.map(projects1 -> {
            ProjectsDTO projectsDTO = convertToDTO(projects1);
            projectsDTO.setDescription("");
            return projectsDTO;
        });
        return DefaultResponse.success(projectsDTOs);
    }

    @GetMapping(params = {"studyType"})
    public ResponseEntity<?> findAllStudiesByStudyType(
            @RequestParam("studyType") String studyType, Pageable pageRequest) {
        Page<Projects> projects = projectsService.getProjectsByStudyType(studyType, pageRequest);
        Page<ProjectsDTO> projectsDTOs = projects.map(projects1 -> {
            ProjectsDTO projectsDTO = convertToDTO(projects1);
            projectsDTO.setDescription("");
            return projectsDTO;
        });
        return DefaultResponse.success(projectsDTOs);
    }

    @GetMapping(value = "/{projectsId}")
    public ResponseEntity<?> getStudy(@PathVariable("projectsId") String projectsId) {
        Projects projects = projectsService.getProject(projectsId);
        return DefaultResponse.success(projects);
    }

    private ProjectsDTO convertToDTO(Projects projects) {
        ProjectsDTO projectsDTO = modelMapper.map(projects, ProjectsDTO.class);
        projectsDTO.setTaxonomyId(projects.getTaxonomies().getTaxonomyId());
        return projectsDTO;
    }
}

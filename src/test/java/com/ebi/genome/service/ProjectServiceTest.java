package com.ebi.genome.service;

import com.ebi.genome.exceptions.project.ProjectAlreadyExists;
import com.ebi.genome.exceptions.project.ProjectNotFound;
import com.ebi.genome.persistence.domain.Project;
import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

    private final int PAGE_NUMBER = 1;
    private final int PAGE_SIZE = 5;
    private final String STUDY_TYPE_SEARCH_TERM = "Case Set";
    private final int TAXONOMY_SEARCH_TERM = 9606;

    private Pageable pageRequest;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService = new ProjectService();

    private Taxonomy taxonomy;

    private List<Project> projects;

    @Before
    public void setUp() throws Exception {

        Sort sort = new Sort(Sort.Direction.ASC, "title");
        pageRequest = new PageRequest(PAGE_NUMBER, PAGE_SIZE, sort);

        taxonomy = new Taxonomy();
        taxonomy.setTaxonomyCommonName("Human");
        taxonomy.setTaxonomyId(9606);
        taxonomy.setTaxonomyScientificName("Homo sapiens");

        // First project
        Project project = new Project();
        project.setProjectId("PRJEB10956");
        project.setTitle("Intra-tumor Genetic Heterogeneity in Rectal Cancer");
        project.setStudyType("Case Set");
        project.setSourceType("Germline");
        project.setEvaCenterName(null);
        project.setDescription("Targetted confirmatory sequencing of heterogenous variants " +
                "detected by exome sequencing of spatially disparate areas from six rectal tumors");
        project.setCenterName("University of Michigan Medical School");
        project.setTaxonomy(taxonomy);


        // Second project
        Project project1 = new Project();
        project1.setProjectId("PRJEB11984");
        project1.setTaxonomy(taxonomy);
        project1.setCenterName("QIMR");
        project1.setDescription("Somatic mutations were identified in the QIMR Berghofer melanoma cell " +
                "line collection using exome sequencing and comparing with matched germline");
        project1.setSourceType("Germline");
        project1.setStudyType("Case Control");
        project1.setTitle("Somatic Mutations in QIMR Berghofer Melanoma Cell Line " +
                "Collection Identified using Exome sequencing");

        projects = new ArrayList<>();
        projects.add(project);
        projects.add(project1);

        when(projectRepository.findOne(anyString())).then((Answer<Project>) invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            String projectId = (String) args[0];
            for (Project project2 : projects) {
                if (project2.getProjectId().equals(projectId)) {
                    return project2;
                }
            }
            return null;
        });

        when(projectRepository.save(any(Project.class))).then((Answer<Project>) invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            return (Project) args[0];
        });
        when(projectRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(projects));

        when(projectRepository.findByStudyTypeContaining(anyString(), any(Pageable.class))).then(
                (Answer<Page<Project>>) invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            String studyType = (String) args[0];
            List<Project> result = new ArrayList<>();
            for (Project project2 : projects) {
                if (project2.getStudyType().contains(studyType)) {
                    result.add(project2);
                }
            }
            return new PageImpl<>(result);
        });

        when(projectRepository.findByTaxonomyIn(anyListOf(Taxonomy.class), any(Pageable.class))).then(
                (Answer<Page<Project>>) invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    List<Project> result = new ArrayList<>();
                    List<Taxonomy> taxonomies = (List<Taxonomy>) args[0];
                    for (Project project2 : projects) {
                        for (Taxonomy taxonomy1 : taxonomies) {
                            if (project2.getTaxonomy() == taxonomy1) {
                                result.add(project2);
                            }
                        }
                    }
                    return new PageImpl<>(result);
                });
        doNothing().when(projectRepository).delete(anyString());
    }

    @Test
    public void getProjects() throws Exception {
        Page<Project> projects = projectService.getProjects(pageRequest);
        Assert.assertEquals(projects.getTotalElements(), 2);
    }

    @Test
    public void getProjectsByTaxonomies() throws Exception {
        Page<Project> projects = projectService.getProjectsByTaxonomies(
                Collections.singletonList(taxonomy), pageRequest);
        Assert.assertEquals(projects.getTotalElements(), 2);
    }

    @Test
    public void getProjectsByStudyType() throws Exception {
        Page<Project> projects = projectService.getProjectsByStudyType("Case Control", pageRequest);
        Assert.assertEquals(projects.getTotalElements(), 1);
        Assert.assertEquals(projects.getContent().get(0).getProjectId(), "PRJEB11984");
    }

    @Test
    public void getProject() throws Exception {
        Project project = projectService.getProject("PRJEB11984");
        Assert.assertNotNull(project);
        Assert.assertEquals(project.getProjectId(), "PRJEB11984");
        Assert.assertEquals(project.getStudyType(), "Case Control");
    }

    @Test(expected = ProjectNotFound.class)
    public void getNotExistsProject() {
        projectService.getProject("not-exist");
    }

    @Test
    public void isProjectExists() throws Exception {
        Assert.assertFalse(projectService.isProjectExists("not-exist"));
        Assert.assertTrue(projectService.isProjectExists("PRJEB11984"));
    }

    @Test(expected = ProjectAlreadyExists.class)
    public void createAlreadyExistProject() throws Exception {
        projectService.createProject(projects.get(0));
    }

    @Test
    public void createProject() {
        Project project = new Project();
        project.setProjectId("a-new-project");
        project = projectService.createProject(project);
        Assert.assertEquals(project.getProjectId(), "a-new-project");
    }

    @Test
    public void updateProject() throws Exception {
        Project project = projectService.updateProject(projects.get(0));
        Assert.assertEquals(project.getProjectId(), projects.get(0).getProjectId());
    }

    @Test(expected = ProjectNotFound.class)
    public void updateNotExistProject() {
        Project project = new Project();
        project.setProjectId("a-new-project");
        projectService.updateProject(project);
    }

    @Test(expected = ProjectNotFound.class)
    public void deleteNotExistProject() throws Exception {
        Project project = new Project();
        project.setProjectId("a-new-project");
        projectService.deleteProject(project);
    }

}
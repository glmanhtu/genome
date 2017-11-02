package com.ebi.genome.restapi;

import com.ebi.genome.persistence.dto.ProjectsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITStudiesHandler {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProject() {
        ProjectsDTO project = testRestTemplate.getForObject("/studies/PRJEB8650", ProjectsDTO.class);
        Assert.assertNotNull(project);
        Assert.assertEquals(project.getTaxonomyId(), 9606);

        project = testRestTemplate.getForObject("/studies/PRJEB6911", ProjectsDTO.class);
        Assert.assertEquals(project.getTaxonomyId(), 10088);
    }

    @Test
    public void testGetAllStudies() throws IOException {
        String response = testRestTemplate.getForObject("/studies", String.class);
        Page<ProjectsDTO> projectsDTOs = objectMapper.readValue(
            response, objectMapper.getTypeFactory().constructParametricType(CustomPageImpl.class, ProjectsDTO.class));
        Assert.assertNotNull(projectsDTOs);
        Assert.assertEquals(projectsDTOs.getTotalElements(), 113);
    }

    @Test
    public void testGetAllStudiesByTaxonomyId() throws IOException {
        String response = testRestTemplate.getForObject("/studies?taxonomyId=199890", String.class);
        Page<ProjectsDTO> projectsDTOs = objectMapper.readValue(
                response, objectMapper.getTypeFactory().constructParametricType(CustomPageImpl.class, ProjectsDTO.class));
        Assert.assertNotNull(projectsDTOs);
        Assert.assertEquals(projectsDTOs.getTotalElements(), 1);
    }

    @Test
    public void testFindAllStudiesByStudyType() throws IOException {
        String response = testRestTemplate.getForObject("/studies?studyType=Control Set", String.class);
        Page<ProjectsDTO> projectsDTOs = objectMapper.readValue(
                response, objectMapper.getTypeFactory().constructParametricType(CustomPageImpl.class, ProjectsDTO.class));
        Assert.assertNotNull(projectsDTOs);
        Assert.assertEquals(projectsDTOs.getTotalElements(), 101);
    }
}

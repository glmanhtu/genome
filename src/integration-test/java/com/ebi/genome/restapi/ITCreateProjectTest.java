package com.ebi.genome.restapi;

import com.ebi.genome.TestConstants;
import com.ebi.genome.WebTestUtil;
import com.ebi.genome.persistence.dto.ProjectDTO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebAppConfiguration
@DatabaseSetup("no-project-entries.xml")
public class ITCreateProjectTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void create_WhenProjectEntryEmpty_ShouldReturnResponseStatusBadRequest() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        mockMvc.perform(
                post("/studies")
                .contentType(TestConstants.APPLICATION_JSON_UTF8)
                .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void create_WhenProjectEntryHasNoProjectId_ShouldReturnValidationErrorAboutMissingProjectIDAsJson()
            throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTitle("Intra-tumor Genetic Heterogeneity in Rectal Cancer");
        projectDTO.setStudyType("Case Set");
        projectDTO.setSourceType("Germline");
        projectDTO.setEvaCenterName(null);
        projectDTO.setDescription("Targetted confirmatory sequencing of heterogenous variants " +
                "detected by exome sequencing of spatially disparate areas from six rectal tumors");
        projectDTO.setCenterName("University of Michigan Medical School");
        projectDTO.setTaxonomyId(6096);

        mockMvc.perform(
                post("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        )
                .andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(1)))
                .andExpect(jsonPath("$.fieldErrors[0].objectName").value("projectId"));
    }

    @Test
    public void create_WhenProjectEntryValid_ShouldReturnResponseStatusCreated() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId("PRJEB10956");
        projectDTO.setTitle("Intra-tumor Genetic Heterogeneity in Rectal Cancer");
        projectDTO.setStudyType("Case Set");
        projectDTO.setSourceType("Germline");
        projectDTO.setEvaCenterName(null);
        projectDTO.setDescription("Targetted confirmatory sequencing of heterogenous variants " +
                "detected by exome sequencing of spatially disparate areas from six rectal tumors");
        projectDTO.setCenterName("University of Michigan Medical School");
        projectDTO.setTaxonomyId(9606);

        mockMvc.perform(
                post("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        )
                .andExpect(status().isOk());
    }
}

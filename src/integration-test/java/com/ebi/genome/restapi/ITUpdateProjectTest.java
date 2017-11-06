package com.ebi.genome.restapi;

import com.ebi.genome.TestConstants;
import com.ebi.genome.WebTestUtil;
import com.ebi.genome.persistence.dto.ProjectDTO;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebAppConfiguration
public class ITUpdateProjectTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @DatabaseSetup("no-project-entries.xml")
    public void update_WhenProjectEntryIsNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId("not-found");
        projectDTO.setTitle("Intra-tumor Genetic Heterogeneity in Rectal Cancer");
        projectDTO.setStudyType("Case Set");
        projectDTO.setSourceType("Germline");
        projectDTO.setEvaCenterName(null);
        projectDTO.setDescription("Targetted confirmatory sequencing of heterogenous variants " +
                "detected by exome sequencing of spatially disparate areas from six rectal tumors");
        projectDTO.setCenterName("University of Michigan Medical School");
        projectDTO.setTaxonomyId(6096);

        mockMvc.perform(
                put("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DatabaseSetup("no-project-entries.xml")
    @ExpectedDatabase(value = "no-project-entries.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void update_WhenProjectEntryIsNotFound_ShouldNotMakeAnyChangesToDatabase() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId("not-found");
        projectDTO.setTitle("Intra-tumor Genetic Heterogeneity in Rectal Cancer");
        projectDTO.setStudyType("Case Set");
        projectDTO.setSourceType("Germline");
        projectDTO.setEvaCenterName(null);
        projectDTO.setDescription("Targetted confirmatory sequencing of heterogenous variants " +
                "detected by exome sequencing of spatially disparate areas from six rectal tumors");
        projectDTO.setCenterName("University of Michigan Medical School");
        projectDTO.setTaxonomyId(6096);

        mockMvc.perform(
                put("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        );
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    public void update_WhenProjectEntryHasNoTitle_ShouldReturnResponseStatusBadRequest() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId("not-found");
        projectDTO.setStudyType("Case Set");
        projectDTO.setSourceType("Germline");
        projectDTO.setEvaCenterName(null);
        projectDTO.setDescription("Targetted confirmatory sequencing of heterogenous variants " +
                "detected by exome sequencing of spatially disparate areas from six rectal tumors");
        projectDTO.setCenterName("University of Michigan Medical School");
        projectDTO.setTaxonomyId(6096);

        mockMvc.perform(
                put("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    public void update_WhenProjectEntryIsValid_ShouldReturnResponseStatusOk() throws Exception {
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
                put("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        )
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    @ExpectedDatabase(value = "update-one-project-entry.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void update_WhenProjectEntryIsValid_ShouldUpdateProjectEntry() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId("PRJEB10956");
        projectDTO.setTitle("genetic variantion data for LYC");
        projectDTO.setStudyType("Case Set");
        projectDTO.setSourceType("Germline");
        projectDTO.setEvaCenterName("Center for Genomic Regulation - CRG (Barcelona); " +
                "Institute of Human Genetics - Helmholtz Zentrum (Munich)");
        projectDTO.setDescription("Targetted confirmatory sequencing of heterogenous variants detected by exome " +
                "sequencing of spatially disparate areas from six rectal tumors");
        projectDTO.setCenterName("University of Michigan Medical School");
        projectDTO.setTaxonomyId(9606);

        mockMvc.perform(
                put("/studies")
                        .contentType(TestConstants.APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(projectDTO))
        );
    }
}

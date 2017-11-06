package com.ebi.genome.restapi;

import com.ebi.genome.TestConstants;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebAppConfiguration
public class ITFindProjectByIdTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @DatabaseSetup("no-project-entries.xml")
    public void findById_WhenProjectEntryIsNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        mockMvc.perform(
                get("/studies/{id}", "not-exists"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DatabaseSetup("no-project-entries.xml")
    public void findById_WhenProjectEntryIsNotFound_ShouldReturnErrorMessageAsJson() throws Exception {
        mockMvc.perform(
                get("/studies/{id}", "not-exists"))
                .andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value("Project not found"));
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    public void findById_WhenProjectEntryIsFound_ShouldReturnResponseStatusOk() throws Exception {
        mockMvc.perform(
                get("/studies/{id}", "PRJEB10956"))
                .andExpect(status().isOk());
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    public void findById_WhenProjectEntryIsFound_ShouldReturnInformationOfFoundProjectEntryAsJson() throws Exception {
        mockMvc.perform(
                get("/studies/PRJEB10956")
                        .accept(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.taxonomyId").value(9606))
                .andExpect(jsonPath("$.studyType").value("Case Set"));
    }
}

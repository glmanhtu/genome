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

import static org.hamcrest.Matchers.hasSize;
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
@DatabaseSetup("two-project-entries.xml")
public class ITFindProjectByStudyTypeTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void findByStudyType_WhenNoProjectEntriesAreFoundWithSearchTerm_ShouldReturnHttpResponseStatusOk()
            throws Exception {
        mockMvc.perform(
                get("/studies").param("studyType", "not-exists")
                        .accept(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void findByStudyType_WhenProjectEntriesAreFoundWithSearchTerm_ShouldReturnHttpResponseStatusOk()
        throws Exception {
        mockMvc.perform(
                get("/studies").param("studyType", "Case Set")
                        .accept(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void findByStudyType_WhenNoProjectEntriesAreFoundWithSearchTerm_ShouldReturnAnEmptyPageAsJson()
        throws Exception {
        mockMvc.perform(
                get("/studies").param("studyType", "not-exists")
                        .accept(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.numberOfElements").value(0));
    }

    @Test
    public void findByStudyType_WhenStudyTypeOfOneProjectEntryContainsTheGivenSearchTerm_ShouldReturnPageThatHasOneProjectEntryAsJson()
        throws Exception {
        mockMvc.perform(
                get("/studies").param("studyType", "Case Set")
                        .accept(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(TestConstants.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.numberOfElements").value(1));
    }
}

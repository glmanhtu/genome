package com.ebi.genome.restapi;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebAppConfiguration
public class ITDeleteProjectTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    public void delete_WhenProjectIsNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        mockMvc.perform(
                delete("/studies/{projectId}", "not-found")
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    @ExpectedDatabase(value = "one-project-entry.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete_WhenProjectIsNotFound_ShouldNotMakeAnyChangesToDatabase() throws Exception {
        mockMvc.perform(
                delete("/studies/{projectId}", "not-found")
        );
    }

    @Test
    @DatabaseSetup("one-project-entry.xml")
    @ExpectedDatabase(value = "no-project-entries.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void delete_WhenProjectIsFound_ShouldDeleteProjectEntryFromDatabase() throws Exception {
        mockMvc.perform(
                delete("/studies/{projectId}", "PRJEB10956")
        );
    }
}

package com.ebi.genome;

import com.ebi.genome.persistence.domain.Project;
import com.ebi.genome.persistence.repository.ProjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITHibernateProjectRepositoryTests {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void findById() {
        Project project = projectRepository.findOne("PRJEB10956");
        Assert.assertNotNull(project);
    }

}

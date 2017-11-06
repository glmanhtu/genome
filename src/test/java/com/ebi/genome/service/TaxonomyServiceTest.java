package com.ebi.genome.service;

import com.ebi.genome.exceptions.taxonomy.TaxonomyNotExist;
import com.ebi.genome.persistence.domain.Taxonomy;
import com.ebi.genome.persistence.repository.TaxonomyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaxonomyServiceTest {

    @Mock
    private TaxonomyRepository taxonomyRepository;

    @InjectMocks
    private TaxonomyService taxonomyService = new TaxonomyService();

    List<Taxonomy> taxonomies = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        Taxonomy taxonomy = new Taxonomy();
        taxonomy.setTaxonomyCommonName("Human");
        taxonomy.setTaxonomyId(9606);
        taxonomy.setTaxonomyScientificName("Homo sapiens");

        Taxonomy taxonomy1 = new Taxonomy();
        taxonomy1.setTaxonomyId(3702);
        taxonomy1.setTaxonomyScientificName("Arabidopsis thaliana");
        taxonomy1.setTaxonomyCommonName("Thale cress");

        taxonomies.add(taxonomy);
        taxonomies.add(taxonomy1);

        when(taxonomyRepository.findOne(anyInt())).then((Answer<Taxonomy>) invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            int taxonomyId = (int) args[0];
            for (Taxonomy taxonomy2 : taxonomies) {
                if (taxonomy2.getTaxonomyId() == taxonomyId) {
                    return taxonomy2;
                }
            }
            return null;
        });

        when(taxonomyRepository.findAll()).thenReturn(taxonomies);
    }

    @Test
    public void getTaxonomy() throws Exception {
        Taxonomy taxonomy = taxonomyService.getTaxonomy(3702);
        Assert.assertNotNull(taxonomy);
        Assert.assertEquals(taxonomy.getTaxonomyId(), 3702);
        Assert.assertEquals(taxonomy.getTaxonomyCommonName(), "Thale cress");
    }

    @Test(expected = TaxonomyNotExist.class)
    public void getNotExistTaxonomy() {
        taxonomyService.getTaxonomy(1111);
    }

    @Test
    public void getTaxonomies() throws Exception {
        List<Taxonomy> taxonomies = taxonomyService.getTaxonomies();
        Assert.assertEquals(taxonomies.size(), 2);
    }

}
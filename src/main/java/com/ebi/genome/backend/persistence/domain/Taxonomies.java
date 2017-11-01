package com.ebi.genome.backend.persistence.domain;

import javax.persistence.Entity;

@Entity
public class Taxonomies {
    private int taxonomyId;

    private String taxonomyCommonName;

    private String taxonomyScientificName;

    public int getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(int taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public String getTaxonomyCommonName() {
        return taxonomyCommonName;
    }

    public void setTaxonomyCommonName(String taxonomyCommonName) {
        this.taxonomyCommonName = taxonomyCommonName;
    }

    public String getTaxonomyScientificName() {
        return taxonomyScientificName;
    }

    public void setTaxonomyScientificName(String taxonomyScientificName) {
        this.taxonomyScientificName = taxonomyScientificName;
    }

    @Override
    public String toString() {
        return "{\"Taxonomies\":{"
                + "\"taxonomyId\":\"" + taxonomyId + "\""
                + ", \"taxonomyCommonName\":\"" + taxonomyCommonName + "\""
                + ", \"taxonomyScientificName\":\"" + taxonomyScientificName + "\""
                + "}}";
    }
}

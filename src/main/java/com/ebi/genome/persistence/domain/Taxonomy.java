package com.ebi.genome.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taxonomies")
public class Taxonomy {

    @Id
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Taxonomy taxonomy = (Taxonomy) o;

        return taxonomyId == taxonomy.taxonomyId;
    }

    @Override
    public int hashCode() {
        return taxonomyId;
    }

    @Override
    public String toString() {
        return "{\"Taxonomy\":{"
                + "\"taxonomyId\":\"" + taxonomyId + "\""
                + ", \"taxonomyCommonName\":\"" + taxonomyCommonName + "\""
                + ", \"taxonomyScientificName\":\"" + taxonomyScientificName + "\""
                + "}}";
    }
}

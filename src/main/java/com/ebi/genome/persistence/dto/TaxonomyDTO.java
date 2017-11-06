package com.ebi.genome.persistence.dto;

public class TaxonomyDTO {
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
}

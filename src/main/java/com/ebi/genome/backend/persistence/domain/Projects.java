package com.ebi.genome.backend.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projects {

    private String projectId;

    private String title;

    private String description;

    private String sourceType;

    private String studyType;

    private String evaCenterName;

    private String centerName;

    @ManyToOne
    @JoinColumn(name = "taxonomy_id", referencedColumnName = "taxonomy_id")
    private Taxonomies taxonomies;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getEvaCenterName() {
        return evaCenterName;
    }

    public void setEvaCenterName(String evaCenterName) {
        this.evaCenterName = evaCenterName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Taxonomies getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(Taxonomies taxonomies) {
        this.taxonomies = taxonomies;
    }

    @Override
    public String toString() {
        return "{\"Projects\":{"
                + "\"projectId\":\"" + projectId + "\""
                + ", \"title\":\"" + title + "\""
                + ", \"description\":\"" + description + "\""
                + ", \"sourceType\":\"" + sourceType + "\""
                + ", \"studyType\":\"" + studyType + "\""
                + ", \"evaCenterName\":\"" + evaCenterName + "\""
                + ", \"centerName\":\"" + centerName + "\""
                + ", \"taxonomies\":" + taxonomies
                + "}}";
    }
}

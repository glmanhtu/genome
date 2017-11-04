package com.ebi.genome.persistence.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Use to transfer object Project over internet
 */
public class ProjectDTO {

    @Size(min = 5, max = 255)
    @NotNull
    private String projectId;

    @Size(min = 5, max = 255)
    @NotNull
    private String title;

    @Size(max = 5000)
    @NotNull
    private String description;

    @Size(min = 5, max = 255)
    @NotNull
    private String sourceType;

    @Size(min = 5, max = 255)
    @NotNull
    private String studyType;

    @Size(max = 255)
    private String evaCenterName;

    @Size(min = 5, max = 255)
    @NotNull
    private String centerName;

    @NotNull
    private int taxonomyId;

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

    public int getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(int taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    @Override
    public String toString() {
        return "{\"ProjectDTO\":{"
                + "\"projectId\":\"" + projectId + "\""
                + ", \"title\":\"" + title + "\""
                + ", \"description\":\"" + description + "\""
                + ", \"sourceType\":\"" + sourceType + "\""
                + ", \"studyType\":\"" + studyType + "\""
                + ", \"evaCenterName\":\"" + evaCenterName + "\""
                + ", \"centerName\":\"" + centerName + "\""
                + ", \"taxonomyId\":\"" + taxonomyId + "\""
                + "}}";
    }
}

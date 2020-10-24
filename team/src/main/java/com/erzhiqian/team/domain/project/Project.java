package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.domain.team.Team;
import com.erzhiqian.team.domain.value.project.Feature;
import com.erzhiqian.team.domain.value.project.Status;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.*;
import static com.erzhiqian.team.domain.value.project.Status.IN_PROGRESS;
import static com.erzhiqian.team.domain.value.project.Status.TO_DO;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
public class Project {

    @Id
    private String identifier;

    private String name;

    private Status status;

    private String assignedTeam;

    private List<Feature> features;

    public Project(String identifier, String name) {
        this(identifier, name, null);
    }

    public Project(String identifier, String name, List<Feature> features) {
        validateIdentifier(identifier, "Error creating '" + name + "' project");
        validateName(name, "Error creating '" + identifier + "' project");
        features = emptyIfNull(features);
        validateFeatures(features, "Error creating '" + name + " 'project");
        this.identifier = identifier;
        setName(name);
        setStatus(TO_DO);
        setFeatures(features);
    }

    private void validateFeatures(List<Feature> features, String message) {
        features.forEach(feature -> validateFeature(feature, message));
    }

    private void validateFeature(Feature feature, String message) {
        when(feature == null)
                .thenInvalidEntity(EMPTY_FEATURE, message);
        when(feature.isUnnamed())
                .thenInvalidEntity(EMPTY_FEATURE_NAME, message);
        when(feature.hasNoStatus())
                .thenInvalidEntity(EMPTY_FEATURE_STATUS, message);
        when(feature.hasInvalidStatus())
                .thenInvalidEntity(INVALID_STATUS, message);
        when(feature.hasNoRequirement())
                .thenInvalidEntity(EMPTY_FEATURE_REQUIREMENT, message);
        when(feature.hasInvalidRequirement())
                .thenInvalidEntity(INVALID_FEATURE_REQUIREMENT, message);
    }


    private void validateIdentifier(String identifier, String message) {
        when(isBlank(identifier))
                .thenInvalidEntity(EMPTY_PROJECT_IDENTIFIER, message);
    }

    private void validateName(String name, String message) {
        when(isBlank(name))
                .thenInvalidEntity(EMPTY_PROJECT_NAME, message);


    }


    private Project() {
    }

    public void rename(String name) {
        validateName(name, "Error renaming '" + identifier + "' project");
        setName(name);
    }

    protected void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

    protected void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }

    protected void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public void updateFeatures(List<Feature> features) {
        features = emptyIfNull(features);
        validateFeatures(features, "Error updating '" + identifier + "' project features");
        setFeatures(features);
    }

    public void assignTeam(Team team) {
        setAssignedTeam(null != team ? team.getName() :null);
    }

    public void start() {
        String message = "Error starting '" + identifier + "' project";
        requireAssignedTeam(message);
        requireUnStarted(message);
        setStatus(IN_PROGRESS);
    }

    private void requireAssignedTeam(String message) {
        when(isBlank(assignedTeam))
                .thenInvalidEntity(UNASSIGNED_TEAM,message);
    }

    private void requireUnStarted(String message) {
        when(status.isAtLeastStarted())
                .thenInvalidEntity(PROJECT_ALREADY_STARTED, message);
    }


}

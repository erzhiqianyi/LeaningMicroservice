package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.domain.value.project.Feature;
import com.erzhiqian.team.domain.value.project.Status;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.*;
import static com.erzhiqian.team.domain.value.project.Status.TODO;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

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
        this.name = name;
        this.status = TODO;
        this.features = features;
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

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}

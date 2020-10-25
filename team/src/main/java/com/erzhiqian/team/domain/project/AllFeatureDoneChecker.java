package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.domain.value.project.Feature;

import java.util.List;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.PROJECT_ENDING_CONDITION_NOT_FULFILLED;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

class AllFeatureDoneChecker implements FeatureChecker {

    @Override
    public void checkFeatures(List<Feature> features, String errorMessage) {
        boolean anyInProgress = emptyIfNull(features)
                .stream()
                .anyMatch(Feature::isInProgress);
        when(anyInProgress)
                .thenInvalidEntity(PROJECT_ENDING_CONDITION_NOT_FULFILLED, errorMessage);
    }
}

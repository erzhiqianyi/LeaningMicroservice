package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.domain.value.project.Feature;

import java.util.List;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.PROJECT_ENDING_CONDITION_NOT_FULFILLED;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

class OnlyNecessaryFeatureDoneChecker implements FeatureChecker {

    @Override
    public void checkFeatures(List<Feature> features, String errorMessage) {
        boolean necessaryNotDone = emptyIfNull(features)
                .stream()
                .anyMatch(Feature::isNecessaryAndUnDone);
        when(necessaryNotDone)
                .thenInvalidEntity(PROJECT_ENDING_CONDITION_NOT_FULFILLED,errorMessage);
    }
}

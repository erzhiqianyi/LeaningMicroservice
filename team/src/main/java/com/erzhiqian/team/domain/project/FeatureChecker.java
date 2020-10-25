package com.erzhiqian.team.domain.project;

import com.erzhiqian.team.domain.value.project.Feature;

import java.util.List;

public interface FeatureChecker {

    void checkFeatures(List<Feature> features, String errorMessage);

    static FeatureChecker resolveFeatureChecker(boolean onlyNecessaryFeatureDone) {
        return onlyNecessaryFeatureDone
                ? new OnlyNecessaryFeatureDoneChecker()
                : new AllFeatureDoneChecker();
    }
}

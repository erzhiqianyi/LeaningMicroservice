package com.erzhiqian.team.domain.value;


import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
public class Employee {

    private String firstName;

    private String lastName;

    private JobPosition jobPosition;

    public Employee(String firstName, String lastName, JobPosition jobPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobPosition = jobPosition;
    }

    private Employee() {
    }

    public boolean hasNotFirstName() {
        return isBlank(firstName);
    }

    public boolean hasNotLastName() {
        return isBlank(lastName);
    }


    private boolean hasJobPosition() {
        return null != jobPosition;
    }


    public boolean hasNoJobPosition() {
        return !hasJobPosition();
    }

    public boolean hasInvalidJobPosition() {
        return hasJobPosition() && jobPosition.isInvalid();
    }

}


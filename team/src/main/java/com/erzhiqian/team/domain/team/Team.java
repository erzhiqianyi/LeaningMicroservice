package com.erzhiqian.team.domain.team;

import com.erzhiqian.team.domain.value.Employee;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

import static com.erzhiqian.team.domain.exceptions.DomainPreCondition.when;
import static com.erzhiqian.team.domain.exceptions.ErrorCode.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Team {
    @Id
    private String name;

    private int currentlyImplementApps;

    private List<Employee> members;

    public Team(String name) {
        validateName(name, "Error creating team.");
        this.name = name;
        currentlyImplementApps = 0;
        members = new ArrayList<>();
    }

    private Team() {

    }

    private void validateName(String name, String message) {
        when(isBlank(name))
                .thenInvalidEntity(EMPTY_TEAM_NAME, message);
    }

    public String getName() {
        return name;
    }


    public void addMember(Employee member) {
        validateMember(member, "Error adding member to '" + name + "' team.");
        members.add(member);
    }

    private void validateMember(Employee member, String message) {
        when(null == member)
                .thenInvalidEntity(EMPTY_MEMBER, message);
       when(member.hasNotFirstName())
               .thenInvalidEntity(EMPTY_MEMBER_FIRST_NAME,message);
       when(member.hasNotLastName())
               .thenInvalidEntity(EMPTY_MEMBER_LAST_NAME,message);
        when(member.hasNoJobPosition())
                .thenInvalidEntity(EMPTY_MEMBER_JOB_POSITION, message);
        when(member.hasInvalidJobPosition())
                .thenInvalidEntity(INVALID_MEMBER_JOB_POSITION, message);
    }
}

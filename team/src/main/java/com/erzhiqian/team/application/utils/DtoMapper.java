package com.erzhiqian.team.application.utils;

import com.erzhiqian.team.application.dto.TeamMember;
import com.erzhiqian.team.domain.value.Employee;
import com.erzhiqian.team.domain.value.JobPosition;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class DtoMapper {

    private DtoMapper() {

    }

    public static Employee mapToEmployee(TeamMember teamMember) {
        JobPosition jobPosition = mapToJobPosition(teamMember.getJobPosition());
        return new Employee(teamMember.getFirstName(),teamMember.getLastName(),jobPosition);
    }

    private static JobPosition mapToJobPosition(String jobPosition) {
        if (isBlank(jobPosition)) {
            return null;
        }
        try {
            return JobPosition.valueOf(jobPosition);
        } catch (Exception e) {
            return JobPosition.INVALID;
        }


    }

}

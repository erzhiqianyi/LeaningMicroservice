package com.erzhiqian.team.specification.team

import com.erzhiqian.team.BasicSpecification
import com.erzhiqian.team.application.dto.NewTeam
import com.erzhiqian.team.application.dto.TeamMember
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

class TeamSpecification  extends BasicSpecification{
    def "Should create a new team"() {
        given:
        def newTeam = new NewTeam(name: 'Team 1')

        when:
        def response = post('/teams', newTeam)

        then:
        response.statusCode == CREATED
    }

    @Unroll
    def "Should not create an unnamed new team"() {
        given:
        def newTeam = new NewTeam(name: name)

        when:
        def response = post('/teams', newTeam)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == 40002

        where:
        name << [null, '', '  ']
    }

    def "Should not create a team that already exists"() {
        given:
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)

        when:
        def response = post('/teams', newTeam)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == 40001
    }

    @Unroll
    def "Should add a new member with #jobPosition job position to a team"() {
        given:
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)
        def member = new TeamMember(firstName: 'Mariusz', lastName: 'Kopylec', jobPosition: jobPosition)

        when:
        def response = post('/teams/Team 1/members', member)

        then:
        response.statusCode == CREATED

        where:
        jobPosition << ['DEVELOPER', 'SCRUM_MASTER', 'PRODUCT_OWNER']
    }

    @Unroll
    def "Should not add a new member without a first name to a team"() {
        given:
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)
        def member = new TeamMember(firstName: firstName, lastName: 'Kopylec', jobPosition: 'DEVELOPER')

        when:
        def response = post('/teams/Team 1/members', member)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode  == 40005

        where:
        firstName << [null, '', '  ']
    }

    @Unroll
    def "Should not add a new member without a last name to a team"() {
        given:
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)
        def member = new TeamMember(firstName: 'Mariusz', lastName: lastName, jobPosition: 'DEVELOPER')

        when:
        def response = post('/teams/Team 1/members', member)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == 40006

        where:
        lastName << [null, '', '  ']
    }

    def "Should not add a new member with #jobPosition job position to a team"() {
        given:
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)
        def member = new TeamMember(firstName: 'Mariusz', lastName: 'Kopylec', jobPosition: jobPosition)

        when:
        def response = post('/teams/Team 1/members', member)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == errorCode

        where:
        jobPosition            | errorCode
        null                   | 40007
        ''                     | 40007
        '  '                   | 40007
        'INVALID_JOB_POSITION' | 40008
    }

    def "Should not add a new member to a nonexistent team"() {
        given:
        def member = new TeamMember(firstName: 'Mariusz', lastName: 'Kopylec', jobPosition: 'DEVELOPER')

        when:
        def response = post('/teams/Team 1/members', member)

        then:
        response.statusCode == NOT_FOUND
        response.body.errorCode  == 40003
    }

    def "Should browse teams if none exists"() {
        when:
        def response = get('/teams', List)

        then:
        response.statusCode == OK
        response.body == []
    }

}

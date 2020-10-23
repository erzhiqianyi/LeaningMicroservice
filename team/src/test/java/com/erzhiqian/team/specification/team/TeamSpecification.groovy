package com.erzhiqian.team.specification.team

import com.erzhiqian.team.BasicSpecification
import com.erzhiqian.team.application.dto.NewTeam
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.CREATED
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

}

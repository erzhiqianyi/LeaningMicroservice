package com.erzhiqian.wechatappmanager.specification.team

import com.erzhiqian.wechatappmanager.manager.dto.team.NewTeam
import com.erzhiqian.wechatappmanager.specification.BasicSpecification
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.*

class TeamSpecification extends BasicSpecification {

    def "Should create new team and browse it"() {
        given:
        def newTeam = new NewTeam('Team 1')

        when:
        def response = httpClient.createTeam(newTeam)

        then:
        with(response) {
            status == CREATED
        }

        when:
        response = httpClient.getTeams()

        then:
        with(response) {
            status == OK
            body.size() == 1
            with(body[0]) {
                name == 'Team 1'
                currentlyImplementedProjects == 0
                !busy
                members == []
            }
        }
    }

    @Unroll
    def "Should not create an unnamed new team"() {
        given:
        def newTeam = new NewTeam(name)

        when:
        def response = httpClient.createTeam(newTeam)

        then:
        with(response) {
            status == UNPROCESSABLE_ENTITY
            with(failure) {
                message == "Creating '$name' team has failed"
                codes == ['EMPTY_TEAM_NAME']
            }
        }

        where:
        name << [null, '', '  ']
    }

    def "Should not create a team that already exists"() {
        given:
        def newTeam = new NewTeam('Team 1')
        httpClient.createTeam(newTeam)

        when:
        def response = httpClient.createTeam(newTeam)

        then:
        with(response) {
            status == UNPROCESSABLE_ENTITY
            with(failure) {
                message == "Creating 'Team 1' team has failed"
                codes == ['TEAM_EXISTS']
            }
        }
    }




    def "Should browse teams if none exists"() {
        when:
        def response = httpClient.getTeams()

        then:
        with(response) {
            status == OK
            body.isEmpty()
        }
    }
}

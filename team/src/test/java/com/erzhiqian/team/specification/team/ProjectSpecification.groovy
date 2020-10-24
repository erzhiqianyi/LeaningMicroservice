package com.erzhiqian.team.specification.team

import com.erzhiqian.team.BasicSpecification
import com.erzhiqian.team.application.dto.project.NewProjectDraft
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

class ProjectSpecification  extends BasicSpecification{


    def "Should create new project draft"() {
        given:
        def projectDraft = new NewProjectDraft(name: 'Project 1')

        when:
        def response = post('/projects/drafts', projectDraft)

        then:
        response.statusCode == CREATED
    }

    @Unroll
    def "Should not create an unnamed new project draft"() {
        given:
        def projectDraft = new NewProjectDraft(name: name)

        when:
        def response = post('/projects/drafts', projectDraft)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == 40010

        where:
        name << [null, '', '  ']
    }
}

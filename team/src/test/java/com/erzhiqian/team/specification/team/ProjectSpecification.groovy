package com.erzhiqian.team.specification.team

import com.erzhiqian.team.BasicSpecification
import com.erzhiqian.team.application.dto.project.NewFeature
import com.erzhiqian.team.application.dto.project.NewProject
import com.erzhiqian.team.application.dto.project.NewProjectDraft
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
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

    @Unroll
    def "Should create new full project with a #requirement feature"() {
        given:
        def feature = new NewFeature(name: 'Feature 1', requirement: requirement)
        def project = new NewProject(name: 'Project 1', features: [feature])

        when:
        def response = post('/projects', project)

        then:
        response.statusCode == CREATED

        where:
        requirement << ['OPTIONAL', 'RECOMMENDED', 'NECESSARY']
    }

    @Unroll
    def "Should not create an unnamed new full project"() {
        given:
        def project = new NewProject(name: name, features: [])

        when:
        def response = post('/projects', project)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode  == 40010

        where:
        name << [null, '', '  ']
    }

    @Unroll
    def "Should not create a new full project with unnamed feature"() {
        given:
        def feature = new NewFeature(name: name, requirement: 'NECESSARY')
        def project = new NewProject(name: 'Project 1', features: [feature])

        when:
        def response = post('/projects', project)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode   == 40012

        where:
        name << [null, '', '  ']
    }

    @Unroll
    def "Should not create a new full project with feature with #requirement requirement"() {
        given:
        def feature = new NewFeature(name: 'Feature 1', requirement: requirement)
        def project = new NewProject(name: 'Project 1', features: [feature])

        when:
        def response = post('/projects', project)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == errorCode

        where:
        requirement           | errorCode
        null                  | 40014
        ''                    | 40014
        '  '                  | 40014
        'INVALID_REQUIREMENT' | 40015
    }

    def "Should browse projects if none exists"() {
        when:
        def response = get('/projects', List)

        then:
        response.statusCode == OK
        response.body == []
    }


    def "Should not browse project if it does not exist"() {
        when:
        def response = get('/projects/abc', Map)

        then:
        response.statusCode == NOT_FOUND
        response.body.errorCode  == 40016
    }


}

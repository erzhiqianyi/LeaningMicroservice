package com.erzhiqian.team.specification.team

import com.erzhiqian.team.BasicSpecification
import com.erzhiqian.team.application.dto.project.ExistingProjectDraft
import com.erzhiqian.team.application.dto.project.ExistingProject
import com.erzhiqian.team.application.dto.project.NewFeature
import com.erzhiqian.team.application.dto.project.NewProject
import com.erzhiqian.team.application.dto.project.NewProjectDraft
import com.erzhiqian.team.application.dto.project.ProjectFeature
import com.erzhiqian.team.application.dto.project.UpdatedProject
import com.erzhiqian.team.application.dto.team.ExistingTeam
import com.erzhiqian.team.application.dto.team.NewTeam
import org.springframework.core.ParameterizedTypeReference
import spock.lang.Unroll

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
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


    def "Should create new project draft and browse it"() {
        given:
        def projectDraft = new NewProjectDraft(name: 'Project 1')

        when:
        def response = post('/projects/drafts', projectDraft)

        then:
        response.statusCode == CREATED

        when:
        response = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {})

        then:
        response.statusCode == OK
        response.body != null
        response.body.size() == 1
        with(response.body[0]) {
            identifier != null
            name == 'Project 1'
        }

        and:
        def projectIdentifier = response.body[0].identifier

        when:
        response = get("/projects/$projectIdentifier", ExistingProject)

        then:
        response.statusCode == OK
        response.body != null
        with(response.body) {
            identifier == projectIdentifier
            name == 'Project 1'
            status == 'TO_DO'
            team == null
            features == []
        }
    }

   @Unroll
    def "Should create new full project with a #requirement feature and browse it"() {
        given:
        def feature = new NewFeature(name: 'Feature 1', requirement: requirement)
        def project = new NewProject(name: 'Project 1', features: [feature])

        when:
        def response = post('/projects', project)

        then:
        response.statusCode == CREATED

        when:
        response = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {})

        then:
        response.statusCode == OK
        response.body != null
        response.body.size() == 1
        with(response.body[0]) {
            identifier != null
            name == 'Project 1'
        }

        and:
        def projectIdentifier = response.body[0].identifier

        when:
        response = get("/projects/$projectIdentifier", ExistingProject)

        then:
        response.statusCode == OK
        response.body != null
        with(response.body) {
            identifier == projectIdentifier
            name == 'Project 1'
            status == 'TO_DO'
            team == null
            features != null
            features.size() == 1
            features[0].name == 'Feature 1'
            features[0].status == 'TO_DO'
            features[0].requirement == requirement
        }

        where:
        requirement << ['OPTIONAL', 'RECOMMENDED', 'NECESSARY']
    }


    @Unroll
    def "Should update a project setting a #requirement feature with #featureStatus status and browse it"() {
        given:
        def feature = new NewFeature(name: 'Feature 1', requirement: requirement)
        def project = new NewProject(name: 'Project 1', features: [feature])
        post('/projects', project)
        def newTeam = new NewTeam(name: 'Team 2')
        post('/teams', newTeam)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier
        def projectFeature = new ProjectFeature(name: 'Feature 2', status: featureStatus, requirement: requirement)
        def updatedProject = new UpdatedProject(name: 'Project 2', team: 'Team 2', features: [projectFeature])

        when:
        def response = put("/projects/$projectIdentifier", updatedProject)

        then:
        response.statusCode == NO_CONTENT

        when:
        response = get("/projects/$projectIdentifier", ExistingProject)

        then:
        response.statusCode == OK
        response.body != null
        with(response.body) {
            identifier == projectIdentifier
            name == 'Project 2'
            status == 'TO_DO'
            team == 'Team 2'
            features != null
            features.size() == 1
            features[0].name == 'Feature 2'
            features[0].status == featureStatus
            features[0].requirement == requirement
        }

        when:
        response = get('/teams', new ParameterizedTypeReference<List<ExistingTeam>>() {})

        then:
        response.statusCode == OK
        response.body != null
        response.body.size() == 1
        with(response.body[0]) {
            name == 'Team 2'
            currentlyImplementedProjects == 1
            !busy
            members == []
        }

        where:
        featureStatus | requirement
        'TO_DO'       | 'OPTIONAL'
        'IN_PROGRESS' | 'RECOMMENDED'
        'DONE'        | 'NECESSARY'
    }

    @Unroll
    def "Should not update a project with an empty name"() {
        given:
        def project = new NewProject(name: 'Project 1', features: [])
        post('/projects', project)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier
        def updatedProject = new UpdatedProject(name: name, features: [])

        when:
        def response = put("/projects/$projectIdentifier", updatedProject)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == 40010

        where:
        name << [null, '', '  ']
    }

    @Unroll
    def "Should not update a project with unnamed feature"() {
        given:
        def project = new NewProject(name: 'Project 1', features: [])
        post('/projects', project)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier
        def projectFeature = new ProjectFeature(name: name, status: 'IN_PROGRESS', requirement: 'OPTIONAL')
        def updatedProject = new UpdatedProject(name: 'Project 1', features: [projectFeature])

        when:
        def response = put("/projects/$projectIdentifier", updatedProject)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode == 40012

        where:
        name << [null, '', '  ']
    }

    @Unroll
    def "Should not update a project with feature with #status status or #requirement requirement"() {
        given:
        def project = new NewProject(name: 'Project 1', features: [])
        post('/projects', project)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier
        def projectFeature = new ProjectFeature(name: 'Feature 1', status: status, requirement: requirement)
        def updatedProject = new UpdatedProject(name: 'Project 1', features: [projectFeature])

        when:
        def response = put("/projects/$projectIdentifier", updatedProject)

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode ==  errorCode

        where:
        status           | requirement           || errorCode
        null             | 'OPTIONAL'            || 40013
        ''               | 'OPTIONAL'            || 40013
        '  '             | 'OPTIONAL'            || 40013
        'INVALID_STATUS' | 'OPTIONAL'            || 40017
        'DONE'           | null                  || 40014
        'DONE'           | ''                    || 40014
        'DONE'           | '  '                  || 40014
        'DONE'           | 'INVALID_REQUIREMENT' || 40015
    }

    def "Should start a project"() {
        given:
        def project = new NewProject(name: 'Project 1', features: [])
        post('/projects', project)
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier
        def updatedProject = new UpdatedProject(name: 'Project 1', team: 'Team 1', features: [])
        put("/projects/$projectIdentifier", updatedProject)

        when:
        def response = patch("/projects/$projectIdentifier/started")

        then:
        response.statusCode == NO_CONTENT
        with(get("/projects/$projectIdentifier", ExistingProject).body) {
            status == 'IN_PROGRESS'
        }
    }

    def "Should not start an already started project"() {
        given:
        def project = new NewProject(name: 'Project 1', features: [])
        post('/projects', project)
        def newTeam = new NewTeam(name: 'Team 1')
        post('/teams', newTeam)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier
        def updatedProject = new UpdatedProject(name: 'Project 1', team: 'Team 1', features: [])
        put("/projects/$projectIdentifier", updatedProject)
        patch("/projects/$projectIdentifier/started")

        when:
        def response = patch("/projects/$projectIdentifier/started")

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode   == 40018
    }

    def "Should not start a project when no team is assigned to it"() {
        given:
        def project = new NewProject(name: 'Project 1', features: [])
        post('/projects', project)
        def projectIdentifier = get('/projects', new ParameterizedTypeReference<List<ExistingProjectDraft>>() {}).body[0].identifier

        when:
        def response = patch("/projects/$projectIdentifier/started")

        then:
        response.statusCode == UNPROCESSABLE_ENTITY
        response.body.errorCode  == 40019
    }

    def "Should not start a nonexistent project"() {
        when:
        def response = patch('/projects/nonexistent project/started')

        then:
        response.statusCode == NOT_FOUND
        response.body.errorCode  == 40016
    }




}

pipeline {
    agent any
    tools {
        gradle 'Gradle-8.4'
    }
    stages {
        stage('Source') {
            steps {
                bat 'gradle --version'
                bat 'git --version'
                git url: 'https://github.com/khamroevjs/springboot-jenkins.git',
                    branch: 'main',
                    changelog: false,
                    poll: false
            }
        }
        stage('Clean') {
            steps {
                dir("${env.WORKSPACE}") {
                    bat 'gradle clean'
                }
            }
        }
        stage('Test') {
            steps {
                dir("${env.WORKSPACE}") {    
                    bat 'gradle test'
                }
            }
        }
        stage('Build') {
            steps {
                dir("${env.WORKSPACE}") {
                    bat 'gradle war'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-token') {
                    bat 'gradle sonar'
                }
            }
        }
        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat-secret', path: '', url: 'http://localhost:8000/')],
                    contextPath: 'spring-boot', onFailure: false, war: '**/*.war'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/build/libs/springboot-jenkins-1.0-plain.war',
                allowEmptyArchive: true,
                onlyIfSuccessful: true,
                fingerprint: true,
                followSymlinks: false
            
            junit allowEmptyResults: true, testResults: '**/build/test-results/test/TEST-*.xml'
        }
    }
}
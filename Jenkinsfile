pipeline {
    agent any
    tools {
        gradle 'Gradle-8.4'
    }
    // parameters {
    //     string(name: 'TOMCAT_PATH',
    //         defaultValue: 'C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1',
    //         description: 'Enter Tomcat Server 10 folder path')
    // }
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
        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'c4465698-6b86-4ad0-ab00-c4cbe29ac4f6', path: '', url: 'http://localhost:8000/')], 
                    contextPath: 'spring-boot',
                    onFailure: false,
                    war: '${env.WORKSPACE}\\build\\libs\\springboot-jenkins-1.0-plain.war'
            }
        }
        // stage('Deploy') {
        //     steps {
        //         input message: 'Confirm deployment to production...', ok: 'Deploy'
        //         bat "\"${env.TOMCAT_PATH}\\bin\\shutdown.bat\""
        //         bat "copy \"${env.WORKSPACE}\\build\\libs\\springboot-jenkins-1.0-plain.war\" \"${env.TOMCAT_PATH}\\webapps\\spring-boot.war\""
        //         bat "\"${env.TOMCAT_PATH}\\bin\\startup.bat\""
        //     }
        // }
    }
    post {
        always {
            archiveArtifacts artifacts: '**\\build\\libs\\springboot-jenkins-1.0-plain.war',
                allowEmptyArchive: true,
                onlyIfSuccessful: true,
                fingerprint: true,
                followSymlinks: false
        }
    }
}
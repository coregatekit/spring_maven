@Library("jenkins-shared-libraries") _

pipeline {
    agent any

    environment {
        dockerImage = ''
        MAVEN_OPTS = '-Djansi.force=true'
    }

    parameters {
        string(name: 'Tag',
        defaultValue: 'latest',
        description: 'Set tag for docker image')
    }

    tools { 
        maven 'Maven 3.6.2' 
        jdk 'jdk8' 
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        ansiColor('xterm')
    }

    stages {
            stage('Build') {
                steps {
                    javaBuild()
                }
            }
            stage('Test') {
                steps {
                    javaTest()
                }
                post {
                    always {
                        junit 'target/surefire-reports/*.xml'
                    }
                }
            }
            stage('Build image') {
                steps {
                    script {
                        // dockerImage = docker.build("coregatekit/spring-maven:${params.Tag}")
                        dockerImage = buildDocker("coregatekit/spring-maven:${params.Tag}")
                    }
                }
            }
            stage('Push image') {
                steps {
                    script {
                        withDockerRegistry(
                            credentialsId: 'Dockerhub',
                            url: 'https://index.docker.io/v1/'
                        ) {
                            pushDocker("coregatekit/spring-maven:${params.Tag}")
                        }
                    }
                }
            }
            stage('Deploy on K8s') {
                steps {
                    sh 'kubectl apply -f k8s/deployment.yaml';
                    // sh 'kubectl version'
                }
            }
        }
}
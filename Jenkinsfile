pipeline {
    agent any

    environment {
        dockerImage = ''
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
                ansiColor('xterm') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Test') {
            steps {
                ansiColor('xterm') {
                    sh 'mvn test'
                }
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
                    ansiColor('xterm') {
                        dockerImage = docker.build("coregatekit/spring-maven:${params.Tag}")
                    }
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
                        ansiColor('xterm') {
                            dockerImage.push()
                        }
                    }
                }
            }
        }
    }
}


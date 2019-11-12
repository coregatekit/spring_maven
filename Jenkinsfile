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

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
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
                    dockerImage = docker.build("coregatekit/spring-maven:${params.Tag}")
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
                        dockerImage.push()
                    }
                }
            }
        }
    }
}


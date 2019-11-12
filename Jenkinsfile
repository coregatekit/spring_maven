pipeline {
    agent any

    environment {
        dockerImage = ''
    }

    tools { 
        maven 'Maven 3.6.2' 
        jdk 'jdk8' 
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B'
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
                    dockerImage = docker.build("coregatekit/spring-maven")
                }
            }
        }
        stage('Push image') {
            steps {
                script {
                    withDockerRegistry(
                        credentialsId: 'docker-credential',
                        url: 'https://index.docker.io/v1/'
                    ) {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}


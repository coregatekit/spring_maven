pipeline {
    agent {
        dockerfile true
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
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t spring-maven .'
            }
        }
    }
}


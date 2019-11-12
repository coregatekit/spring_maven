pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
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
    }
}


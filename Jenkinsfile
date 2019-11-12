pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.2' 
        jdk 'jdk8' 
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvnw -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvnw test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') { 
            steps {
                sh './jenkins/scripts/deliver.sh' 
            }
        }
    }
}
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
        maven 'maven' 
        jdk 'jdk' 
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
            stage('Sonarqube') {
                // environment {
                //     scannerHome = tool 'sonarqube-scanner'
                // }
                steps {
                    // withSonarQubeEnv('sonarqube') {
                    //     sh "${scannerHome}/bin/sonar-scanner"
                    // }
                    // timeout(time: 10, unit: 'MINUTES') {
                    //     waitForQualityGate abortPipeline: true
                    // }
                    sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=spring-maven \
                    -Dsonar.host.url=http://52.76.237.240:9000 \
                    -Dsonar.login=springmaven
                    """
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
            // stage('Push image') {
            //     steps {
            //         script {
            //             withDockerRegistry(
            //                 credentialsId: 'Dockerhub',
            //                 url: 'https://index.docker.io/v1/'
            //             ) {
            //                 pushDocker("coregatekit/spring-maven:${params.Tag}")
            //             }
            //         }
            //     }
            // }
            // stage('Deploy on K8s') {
            //     steps {
            //         sh 'kubectl apply -f k8s/deployment.yaml';
            //         // sh 'kubectl version'
            //     }
            // }
        }
}
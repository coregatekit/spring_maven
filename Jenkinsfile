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
                    -Dsonar.host.url=http://35.240.192.26:9000 \
                    -Dsonar.login=16fa4f0bbe97d9ce9131b733743ecd263a4d6d76
                    """
                }
            }

            stage('Nexus upload') {
                steps {
                    // sh 'mkdir zip'
                    zip zipFile: './zip/test.zip', archive: false, dir: ''
                    nexusArtifactUploader artifacts: [[artifactId: 'Spring-Maven', classifier: '', file: './zip/test.zip', type: 'zip']], credentialsId: 'nexus', groupId: 'com.example', nexusUrl: '35.240.192.26:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'Spring-Repo/', version: '0.0.1-SNAPSHOT'
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
                            credentialsId: 'dockerhub-komchan',
                            url: 'https://index.docker.io/v1/'
                        ) {
                            pushDocker("coregatekit/spring-maven:${params.Tag}")
                            pushDocker("coregatekit/spring-maven:latest")
                        }
                    }
                }
            }

            // stage('Deploy on K8s') {
            //     steps {
            //         sh 'kubectl apply -f k8s/deployment.yaml';
            //         // sh 'kubectl version'
            //     }
            // }
        }
}
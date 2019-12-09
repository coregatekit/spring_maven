@Library("jenkins-shared-libraries") _

pipeline {
    agent any

    environment {
        dockerImage = ''
        MAVEN_OPTS = '-Djansi.force=true'
        latestTag = ''
    }

    // parameters {
    //     string(name: 'Tag',
    //     defaultValue: "latest",
    //     description: 'Set tag for docker image')
    // }

    parameters {
        gitParameter name: 'TAG',
                     type: 'PT_TAG',
                     defaultValue: 'master'
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
            stage('Example') {
                steps {
                    checkout([$class: 'GitSCM',
                            branches: [[name: "${params.TAG}"]],
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [],
                            gitTool: 'Default',
                            submoduleCfg: [],
                            userRemoteConfigs: [[url: 'https://github.com/coregatekit/spring_maven.git']]
                            ])
                }
            }

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
                    -Dsonar.host.url=http://34.87.28.55:9000 \
                    -Dsonar.login=fa6089b68edac67efe49890536ed918982356378
                    """
                }
            }

            stage('Nexus upload') {
                steps {
                    // sh 'mkdir zip'
                    // zip zipFile: './zip/test.zip', archive: false, dir: ''
                    nexusArtifactUploader artifacts: [[artifactId: 'Spring-Maven', classifier: '', file: './target/Spring-Maven-0.0.1-SNAPSHOT.jar', type: 'jar']], credentialsId: 'nexus-joekim', groupId: 'com.example', nexusUrl: '34.87.28.55:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'Spring-Repo/', version: '0.0.1-SNAPSHOT'
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
            //                 credentialsId: 'coregatekit-dockerhub',
            //                 url: 'https://index.docker.io/v1/'
            //             ) {
            //                 pushDocker("coregatekit/spring-maven:${params.Tag}")
            //                 pushDocker("coregatekit/spring-maven:latest")
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
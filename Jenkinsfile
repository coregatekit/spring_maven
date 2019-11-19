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
                    sh 'mvn -B -Dstyle.color=always -DskipTests clean package'
                }
            }
            stage('Test') {
                steps {
                    sh 'mvn -Dstyle.color=always test'
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
            // stage('Push image') {
            //     steps {
            //         script {
            //             withDockerRegistry(
            //                 credentialsId: 'Dockerhub',
            //                 url: 'https://index.docker.io/v1/'
            //             ) {
            //                 dockerImage.push()
            //             }
            //         }
            //     }
            // }
            stage('Deploy') {
                steps {
                    kubernetesDeploy configs: 'config', kubeConfig: [path: '/home/.kube/'], kubeconfigId: 'kubernetes', secretName: '', ssh: [sshCredentialsId: '*', sshServer: ''], textCredentials: [certificateAuthorityData: '', clientCertificateData: '', clientKeyData: '', serverUrl: 'https://10.0.15.14']
                    sh 'kubectl apply -f deployment.yml'
                }
            }
        }
}
@Library("jenkins-shared-libraries") _

podTemplate(containers: [
    containerTemplate(name: 'maven', image 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: 'cat')
]) {
    environment {
        dockerImage = ''
        MAVEN_OPTS = '-Djansi.force=true'
    }

    parameters {
        string(name: 'Tag',
        defaultValue: 'latest',
        description: 'Set tag for docker image')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        ansiColor('xterm')
    }

    node(POD_LABEL) {
        stage('Pull project') {
            git 'https://github.com/coregatekit/spring-maven-onkube.git'
            conatiner('maven') {
                step('Build') {
                    javaBuild()
                }
            }
        }

        stage('Test') {
            step {
                javaTest()
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build image') {
            step {
                script {
                    dockerImage = buildDocker("coregatekit/spring-maven:${params.Tag}")
                }
            }
        }

        stage('Push image') {
            step {
                script {
                    withDockerRegistry(
                        credentialsId: 'Dockerhub',
                        url: 'https://index.docker.io/v1'
                    ) {
                        pushDocker("coregatekit/spring-maven:${params.Tag}")
                    }
                }
            }
        }

        stage('Deploy on K8s') {
            step {
                script {
                    sh 'kubectl apply -f deployment.yml'
                }
            }
        }
    }
}
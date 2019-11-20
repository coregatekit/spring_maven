@Library("jenkins-shared-libraries") _

pipeline {
    agent { label 'slave0' }

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

    stages {
        stage('Kubernetes Connection') {
            steps {
                'kubectl version'
            }
        }
        // stage('Build') {
        //     steps {
        //         javaBuild()
        //     }
        // }

        // stage('Tests') {
        //     steps {
        //         javaTest()
        //     }
        // }

        // stage('Build image') {
        //     steps {
        //         script {
        //             docker.withRegistry(
        //             credentialsId: 'Dockerhub',
        //             url: 'https://index.docker.io/v1') {
        //                 docker.build("coregatekit/spring-maven:${params.Tag}", '.')
        //         }
        //         }

        //         // dockerImage = buildDocker("coregatekit/spring-maven:${params.Tag}")
        //     }
        // }

        // stage('Push image') {
        //     steps {
        //         script {
        //             docker.withRegistry(
        //                 credentialsId: 'Dockerhub',
        //                 url: 'https://index.docker.io/v1'
        //             ) {
        //                 docker.push()
        //             }
        //         }
        //     }
        // }

        // stage('Deploy') {
        //     steps {
        //         script {
        //             deployToKubernetes(${params.Tag}, 'kubeconfig')
        //         }
        //     }
        // }
    }
}

// void deployToKubernetes(String versionName, String credentialsId) {

//     String imageName = "coregatekit/spring-maven:${versionname}"

//     withCredentials([file(credentialsId: credentialsId, variable: 'kubeconfig')]) {

//         withEnv(["IMAGE_NAME=${imageName}"]) {

//             kubernetesDeploy(
//                     credentialsType: 'KubeConfig',
//                     kubeConfig: [path: kubeconfig],
//                     configs: 'k8s/deployment.yaml',
//                     enableConfigSubstitution: true
//             )
//         }
//     }
// }
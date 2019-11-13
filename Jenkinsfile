@Library("jenkins-shared-libraries") _

node () {

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
    
    tools { 
        maven 'Maven 3.6.2' 
        jdk 'jdk8' 
    }

    stage("Build") {
        javaBuild()
    }

    stage("Test") {
        javaTest()
    }
}
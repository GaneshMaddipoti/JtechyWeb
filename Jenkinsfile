pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                sh label: '', script: 'echo "clean"'
                sh label: '', script: 'fuser -k 8090/tcp &'
            }
        }
        stage('build') {
            steps {
                sh label: '', script: 'echo "build"'
                sh label: '', script: 'pwd'
                sh label: '', script: 'cd /home/ec2-user/.jenkins/workspace/JtechyWeb_1'
                sh label: '', script: 'mvn clean install'
            }
        }
        stage("deploy") {
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh label: '', script: 'echo "deploy"'
                    sh label: '', script: 'java -jar target/JtechyWeb-1.0-SNAPSHOT.jar > jtechyweb.log &'
                }
            }
        }
    }
}


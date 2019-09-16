pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('clean') {
            steps {
                sh 'echo "clean workspace"'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage("deploy") {
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh 'run JtechyWeb'
                }
            }
        }
    }
}


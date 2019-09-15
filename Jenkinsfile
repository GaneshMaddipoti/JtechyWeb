pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('clean') {
            steps {
                sh './stopApp.sh'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage("Staging") {
            steps {
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh './startApp.sh'
                }
            }
        }
    }
}


pipeline {
    agent { docker { image 'maven_sudo' } }
    stages {
        stage('clean') {
            steps {
                sh label: '', script: 'echo "clean"'
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
                    sh label: '', script: 'echo "run JtechyWeb"'
                    sh label: '', script: 'sudo java -jar target/JtechyWeb-1.0-SNAPSHOT.jar'
                }
            }
        }
    }
}


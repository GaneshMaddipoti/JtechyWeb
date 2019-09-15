pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('clean') {
            steps {
                sh 'sudo fuser -k 80/tcp'
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
                    sh 'sudo java -jar /home/ec2-user/.jenkins/workspace/JtechyWeb_master/target/JtechyWeb-1.0-SNAPSHOT.jar > jtechy.log &'
                }
            }
        }
    }
}


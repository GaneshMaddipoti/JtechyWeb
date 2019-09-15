pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage("Staging") {
            steps {
                sh 'pid=\$(lsof -i:80 -t); kill -TERM \$pid || kill -KILL \$pid'
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh 'nohup ./mvnw spring-boot:run -Dserver.port=80 &'
                }
            }
        }
    }
}


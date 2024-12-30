pipeline {
    agent any
    stages {
        stage("Build Project") {
            steps {
                sh '''
                mvn clean
                mvn package
                '''
            }
        }
    }
    stages {
       stage("Build Docker Image") {
           steps {
               sh '''
                    docker-compose up
                 '''
                }
            }
        }
}

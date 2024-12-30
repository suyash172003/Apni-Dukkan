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


       stage("Build Docker Image") {
           steps {
               sh '''
                    docker-compose up
                 '''
                }
            }
        }
}

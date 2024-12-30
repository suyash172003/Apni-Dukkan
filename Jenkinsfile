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

        stage("Create image of Project") {
           steps {
              sh '''
              docker-compose up
              '''
            }
        }
     }
}

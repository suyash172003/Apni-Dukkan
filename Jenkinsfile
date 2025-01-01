pipeline {
    agent any
    stages {
        stage("User"){
            steps {
                sh '''
                whoami
                '''
            }
        }
        stage("Build Project") {
            steps {
                sh '''
                mvn clean
                mvn package
                '''
            }
        }

        stage("Create Image and Build") {
           steps {
              sh '''
              docker build -t suyash172003/apni-dukkan-app:${BUILD_NUMBER} .
              docker push suyash172003/apni-dukkan-app:${BUILD_NUMBER}
              '''
            }
        }
    }
}

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

        stage("Create Image and Build") {
           steps {
              sh '''
              docker rmi suyash172003/apni-dukkan-app:latest
              docker build -t suyash172003/apni-dukkan-app:latest .
              docker push suyash172003/apni-dukkan-app:latest
              '''
            }
        }
     }
}

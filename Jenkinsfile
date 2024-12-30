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
              docker login
              docker build -t suyash172003/apni-dukkan-app .
              docker push suyash172003/apni-dukkan-app
              '''
            }
        }
     }
}

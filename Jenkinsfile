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
              docker build -t suyash172003/apni-dukkan-app:latest .
              docker push suyash172003/apni-dukkan-app:latest
              '''
            }
        }

        stage("Deploy Stage") {
                   steps {
                      sh '''
                      echo "Deploy stage"
                      '''
                    }
                }
     }
}

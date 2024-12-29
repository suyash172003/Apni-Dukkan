pipeline{
    agent any
    stages{
        stage("environment"){
            steps{
                script{
                    sh '''
                    apt-get update
                    apt install java
                    '''
                }
            }
        }
        stage("build"){
            steps{
               script{
                  sh '''
                     mvn clean
                     mvn package
                     '''
               }
            }
        }
    }
}
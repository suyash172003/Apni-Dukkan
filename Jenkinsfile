pipeline {
    agent any
    stages {
        stage("Environment Setup") {
            steps {
                script {
                    sh '''
                    set -e
                    sudo apt-get update
                    sudo apt-get install -y openjdk-11-jdk
                    '''
                }
            }
        }
        stage("Build Project") {
            steps {
                script {
                    sh '''
                    set -e
                    mvn clean
                    mvn package
                    '''
                }
            }
        }
    }
}

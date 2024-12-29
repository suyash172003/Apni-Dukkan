pipeline {
    agent {
        docker {
            image 'maven:3.8.4-openjdk-11'
        }
    }
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
}

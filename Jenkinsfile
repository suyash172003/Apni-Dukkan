pipeline {
    agent {
        docker {
            image 'openjdk'
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

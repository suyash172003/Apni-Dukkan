pipeline {
    agent any
    stages {
        stage("Build Project") {
            steps {
                sh '''
                docker-compose up
                '''
            }
        }
    }
}

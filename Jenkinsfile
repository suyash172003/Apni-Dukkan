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
     }
}

pipeline {
    agent any
    environment {
        GIT_REPO_NAME = "Apni-Dukkan"
        GIT_USER_NAME = "suyash172003"
        GIT_EMAIL = "suyash172003@gmail.com"
        BUILD_IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage("User") {
            steps {
                sh '''
                whoami
                sudo chown root:docker /var/run/docker.sock
                sudo ls -l /var/run/docker.sock
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
                docker build -t suyash172003/apni-dukkan-app:${BUILD_IMAGE_TAG} .
                docker push suyash172003/apni-dukkan-app:${BUILD_IMAGE_TAG}
                '''
            }
        }

        stage("Update Deployment file") {
            steps {
                withCredentials([string(credentialsId: 'github', variable: 'GITHUB_TOKEN')]) {
                    sh '''
                    git config user.email "${GIT_EMAIL}"
                    git config user.name "${GIT_USER_NAME}"

                    # Update the Kubernetes deployment file with the build number
                    sed -i "s/apni-dukkan-app:[^ ]*/apni-dukkan-app:${BUILD_IMAGE_TAG}/g" K8/manifests/app_deployment.yml

                    # Commit and push the change
                    git add .
                    git commit -m "Change deployment image"
                    git push https://${GITHUB_TOKEN}@github.com/${GIT_USER_NAME}/${GIT_REPO_NAME} HEAD:main
                    '''
                }
            }
        }
    }
}

pipeline {
            steps {
                git branch: 'main', url: 'https://github.com/ershrikant002/insurance-microservices.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_HUB/$IMAGE_NAME:latest .'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push $DOCKER_HUB/$IMAGE_NAME:latest'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker stop insurance-app || true
                docker rm insurance-app || true

                docker run -d \
                  --name insurance-app \
                  -p 8081:8080 \
                  $DOCKER_HUB/$IMAGE_NAME:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}

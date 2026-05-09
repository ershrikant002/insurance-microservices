pipeline {
agent any
    
tools {
    maven 'Maven3'
    jdk 'JDK17'
}

environment {
    DOCKER_HUB = 'ershrikant002'
    IMAGE_NAME = 'insurance-microservices'
}

stages {

    stage('Checkout Code') {
        steps {
            git branch: 'main',
            url: 'https://github.com/ershrikant002/insurance-microservices.git'
        }
    }

    stage('Check Structure') {
        steps {
            sh 'pwd'
            sh 'find . -name pom.xml'
        }
    }

    stage('Build Services') {
        steps {

            dir('user-service') {
                sh 'mvn clean install -DskipTests'
            }

            dir('policy-service') {
                sh 'mvn clean install -DskipTests'
            }

           
        }
    }

    stage('Run Unit Tests') {
        steps {

            dir('user-service') {
                sh 'mvn test'
            }

            dir('policy-service') {
                sh 'mvn test'
            }

            dir('order-service') {
                sh 'mvn test'
            }
        }
    }

    stage('Docker Login') {
        steps {

            withCredentials([
                usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'ershrikant002',
                    passwordVariable: '3DOT3equal3'
                )
            ]) {

                sh '''
                echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                '''
            }
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

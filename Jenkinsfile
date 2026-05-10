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

                sh '''
                echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                '''
            }
        }
    }
    stage('Deploy to Nexus') {
steps {
    dir('user-service') {

        withCredentials([
            usernamePassword(
                credentialsId: 'nexus-creds',
                usernameVariable: 'NEXUS_USER',
                passwordVariable: 'NEXUS_PASS'
            )
        ]) {

            sh """
            cd user-service
            mvn clean install deploy -DskipTests \
            -Dnexus.username=${NEXUS_USER} \
            -Dnexus.password=${NEXUS_PASS}
            """
        }
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

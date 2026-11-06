pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out source code'
                checkout scm
            }
        }

        stage('Verify Java and Maven') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Maven Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Execute Selenium Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            junit testResults: '**/target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }

        success {
            echo 'Automation execution successful!'
        }

        failure {
            echo 'Automation execution failed. Check console output.'
        }
    }
}

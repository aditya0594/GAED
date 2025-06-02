pipeline {
    agent any

    tools {
        maven 'apache-maven-3.9.6-bin'
        jdk 'jdk-20'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/aditya0594/GAED.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
}
pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/your-repo.git'
            }
        }

        stage('Validate Java & Maven') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Clean Project') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Extent / Test Reports') {
            steps {
                echo 'Reports available under target/surefire-reports'
                echo 'Extent report (if configured) under target/extent-reports'
            }
        }
    }

    post {

        always {
            echo 'Archiving test reports...'

            archiveArtifacts artifacts: '''
                target/surefire-reports/**
                target/extent-reports/**
            ''', allowEmptyArchive: true
        }

        success {
            echo 'Build SUCCESS ✅'
        }

        failure {
            echo 'Build FAILED ❌ - check logs'
        }
    }
}
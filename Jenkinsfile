pipeline {

```
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
            git branch: 'master',
                url: 'https://github.com/AdityaJadhavrao/Orange_HRM_Automation.git'
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

    stage('Verify Reports') {
        steps {
            sh 'ls -la'
            sh 'ls -la test-output || true'
            sh 'ls -la target || true'
        }
    }
}

post {

    always {

        echo 'Archiving reports...'

        archiveArtifacts artifacts: '''
            target/surefire-reports/**
            test-output/**
        ''', allowEmptyArchive: true

        publishHTML([
            allowMissing: true,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'test-output',
            reportFiles: 'ExtentReport.html',
            reportName: 'Extent Automation Report'
        ])
    }

    success {
        echo 'Build SUCCESS'
    }

    failure {
        echo 'Build FAILED - Check console logs'
    }
}
```

}

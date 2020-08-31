pipeline {
    agent any

    environment {
        CI = 'true'
    }

    stages {

        stage('Test') {
            steps {
                echo 'test'
                bat 'gradle test'
            }
        }

        stage('Build') {
            steps {
                echo 'build'
                bat 'gradle build'
            }
        }
        
        stage('JavacocoPublisher') {
            steps {
                javacoco()
            }
        }

        stage ('OWASP Dependency-Check Vulnerabilities') {
            steps {
                dependencyCheck additionalArguments: '''
                    -o "./"
                    -s "./"
                    -f "ALL"
                    --prettyPrint''', odcInstallation: 'OWASP-DC'

                dependencyCheckPublisher pattern: 'dependency-check-report.xml'
            }
        }
    }
}

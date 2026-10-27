
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Source code checked out from GitHub'
            }
        }

        stage('Verify Java and Maven') {
            steps {
                sh '''
                    export PATH="/opt/homebrew/bin:$PATH"
                    java -version
                    mvn -version
                '''
            }
        }

        stage('Test') {
            steps {
                sh '''
                    export PATH="/opt/homebrew/bin:$PATH"
                    mvn -B clean test
                '''
            }
        }

        stage('Package') {
            steps {
                sh '''
                    export PATH="/opt/homebrew/bin:$PATH"
                    mvn -B package -DskipTests
                '''
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/surefire-reports/*.xml'
        }

        success {
            archiveArtifacts artifacts: 'target/*.jar',
                             fingerprint: true
            echo 'Build completed successfully!'
        }

        failure {
            echo 'Build failed. Check the Console Output.'
        }
    }
}

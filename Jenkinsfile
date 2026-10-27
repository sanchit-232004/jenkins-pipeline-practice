
pipeline {
    agent any

    environment {
        DOCKER = '/Users/sanchitbhaskarbadwaik/.docker/bin/docker'
        IMAGE_NAME = 'jenkins-springboot-practice'
    }

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

        stage('Build Docker Image') {
    steps {
        sh '''
            export PATH="/Users/sanchitbhaskarbadwaik/.docker/bin:/Applications/Docker.app/Contents/Resources/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH"

            "$DOCKER" version

            "$DOCKER" build \
                -t "$IMAGE_NAME:$BUILD_NUMBER" \
                -t "$IMAGE_NAME:latest" .
        '''
    }
}


        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                    export PATH="/Users/sanchitbhaskarbadwaik/.docker/bin:/Applications/Docker.app/Contents/Resources/bin:/opt/homebrew/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH"

                    kubectl config current-context
                    kubectl get deployments

                    kubectl set image deployment/springboot-deployment \
                        springboot-container="$IMAGE_NAME:$BUILD_NUMBER"

                    kubectl rollout status deployment/springboot-deployment \
                        --timeout=120s

                    kubectl get pods
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
            echo 'Build, tests, packaging, and Docker image build completed!'
        }

        failure {
            echo 'Pipeline failed. Check the Console Output.'
        }
    }
}

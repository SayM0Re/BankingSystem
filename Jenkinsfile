pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn clean compile test-compile'
            }
        }

        stage('Test') {
            when {
                expression { env.BRANCH_NAME?.startsWith('feature/') }
            }
            steps {
                bat "mvn test"
            }
        }

        stage('Static Analysis') {
            when {
                branch 'develop'
            }
            steps {
                bat 'mvn checkstyle:check pmd:check spotbugs:check'
            }
        }

        stage('Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Install') {
            steps {
                bat 'mvn install'
            }
        }

        stage('Check Coverage') {
            steps {
                bat 'mvn jacoco:check'
            }
        }

        stage('Publish Artifact') {
            steps {
                bat '''
                    if not exist prac\\deploy mkdir prac\\deploy
                    copy prac\\app\\target\\*.jar prac\\deploy\\
                    '''
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            jacoco()
        }
    }
}

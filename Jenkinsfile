pipeline {
    agent any

    tools {
        jdk 'jdk-22'
        maven 'maven-3.9.6'
    }

    environment {
        MAVEN_HOME = tool 'maven-3.9.6'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn" clean compile test-compile'
            }
        }

        stage('Test') {
            when {
                expression { env.BRANCH_NAME?.startsWith('feature/') }
            }
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn" test'
            }
        }

        stage('Static Analysis') {
            when {
                branch 'develop'
            }
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn" checkstyle:check pmd:check spotbugs:check'
            }
        }

        stage('Coverage') {
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn" jacoco:report'
            }
        }

        stage('Install') {
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn" install'
            }
        }

        stage('Check Coverage') {
            steps {
                bat '"%MAVEN_HOME%\\bin\\mvn" jacoco:check'
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

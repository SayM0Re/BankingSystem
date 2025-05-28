pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" clean compile test-compile"
            }
        }

        stage('Test') {
            when {
                expression { env.BRANCH_NAME?.startsWith('origin/feature/') }
            }
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" test"
            }
        }

        stage('Static Analysis') {
            when {
                branch 'develop'
            }
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" checkstyle:check pmd:check"
            }
        }

        stage('Coverage') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" jacoco:report"
            }
        }

        stage('Install') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" install"
            }
        }

        stage('Check Coverage') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" clean verify"
            }
        }

        stage('Publish Artifact') {
            steps {
                bat '''
                    mkdir deploy || echo
                    copy "app\\target\\*.jar" deploy
                '''
                archiveArtifacts artifacts: 'deploy/*.jar', onlyIfSuccessful: true
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            
            // Сохраняем отчеты JaCoCo как артефакты
            archiveArtifacts artifacts: '**/target/site/jacoco/**', allowEmptyArchive: true
            
            // Сохраняем отчеты Checkstyle
            archiveArtifacts artifacts: '**/target/checkstyle-result.xml', allowEmptyArchive: true
        }
    }
}

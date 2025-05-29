pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'maven'
    }

    stages {
        stage('1. Git Import') {
            steps {
                checkout scm
            }
        }

        stage('2. Compile & Test') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" clean compile test-compile"
            }
        }

        stage('3. Test for feature') {
            when {
                expression { env.BRANCH_NAME?.startsWith('origin/feature/') }
            }
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" test"
            }
        }

        stage('4. Static Analysis for develop') {
            when {
                branch 'develop'
            }
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" pmd:check"
            }
        }

        stage('5. Coverage') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" jacoco:report"
            }
        }

        stage('6. Install Artifacts') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" install"
            }
        }

        stage('7. Check Coverage') {
            steps {
                bat "\"%MAVEN_HOME%\\bin\\mvn\" clean verify"
            }
        }

        stage('8. Publish Artifact') {
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
            
            // Сохраняем отчеты JaCoCo
            archiveArtifacts artifacts: '**/target/site/jacoco/**', allowEmptyArchive: true
            
            // Сохраняем отчеты PMD
            archiveArtifacts artifacts: '**/target/site/pmd.xml', allowEmptyArchive: true
        }
    }
}

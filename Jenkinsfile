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
                sh "${MAVEN_HOME}/bin/mvn clean compile test-compile"
            }
        }

        stage('Test') {
            when {
                expression { env.BRANCH_NAME.startsWith('feature/') }
            }
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
                junit 'core/target/surefire-reports/*.xml'
            }
        }

        stage('Static Analysis') {
            when {
                branch 'develop'
            }
            steps {
                sh "${MAVEN_HOME}/bin/mvn checkstyle:check pmd:check spotbugs:check"
            }
        }

        stage('Coverage') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn jacoco:report"
                publishHTML(target: [
                    reportDir: 'core/target/site/jacoco',
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Coverage Report'
                ])
            }
        }

        stage('Install') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn install"
            }
        }

        stage('Check Coverage') {
            steps {
                script {
                    echo 'Проверка покрытия'
                }
            }
        }

        stage('Publish Artifact') {
            steps {
                sh 'cp prac/app/target/*.jar prac/deploy/'
            }
        }
    }

    post {
        failure {
            echo 'Build failed!'
        }
        success {
            echo 'Build succeeded!'
        }
    }
}

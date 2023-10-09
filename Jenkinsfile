pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                echo "Clean Started"
                sh(/mvn -file pom.xml clean/)
                echo "Clean End"
            }
        }
        stage('Compile') {
            steps {
                echo "Code Compilation Started"
                sh(/mvn -file pom.xml compile/)
                echo "Code Compilation End"
            }
        }
        stage('Test') {
            steps {
                echo "Test Started"
                sh(/mvn -file pom.xml test/)
                echo "Test End"
            }
        }
        stage('Result') {
            steps {
                echo "Result Started"
                junit '**/test-output/emailable-report.html'
                echo "Result End"
            }
        }
    }
}
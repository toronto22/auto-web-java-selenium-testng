pipeline {
    agent any

    tools{
        maven 'maven'
        jdk 'jdk9'
    }

    stages {

        stage('Tests') {
            steps {
                sh 'mvn clean install'
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}
}
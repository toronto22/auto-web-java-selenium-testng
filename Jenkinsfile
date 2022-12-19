 pipeline {
     agent any

     tools{
         maven 'maven'
     }

     stages {
         stage('Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                    sh 'mvn clean install'
                }
            }
         }

         stage('reports') {
             steps {
                 script {
                         allure([
                                 includeProperties: false,
                                 jdk: '',
                                 properties: [],
                                 reportBuildPolicy: 'ALWAYS',
                                 results: [[path: 'target/allure-results']]
                         ])
                 }
             }
         }
     }
 }



 pipeline {
     agent any

     tools{
         maven 'maven'
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



 pipeline {
     agent any

     tools{
         maven 'maven'
         jdk 'jdk9'
     }

     stages {

         stage('Tests') {
             steps {
                 bat 'mvn clean install'
                 junit '**/target/surefire-reports/*.xml'
             }
         }
     }
 }



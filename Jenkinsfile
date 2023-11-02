pipeline {
    agent any 
       stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                git 'https://github.com/TruongNgocHuu/Ecommerce'
            }
        }
        stage('Build') {
            steps {
                dir('starter_code') {
                    script {
                        if (isUnix()) {
                            sh 'mvn clean package'
                        } else {
                            bat 'mvn clean package'
                            echo 'Success'
                        }
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                dir('starter_code/target') {
                    script {
                        bat 'copy Ecommerce-Application-0.0.1-SNAPSHOT.war D:\\Deploy\\'
                    }
                }
            }
        }
    }
}

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
                        }
                    }
                }
            }
            steps {
                // Copy the built artifact to the deployment location on the local machine
                sh 'cp path/to/repository/folder/target/your-application.jar /path/to/deployment/location'
                
                // Additional deployment steps if needed
                // sh '...'
            }
        }
    }
}

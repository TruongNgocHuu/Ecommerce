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
                script{
                    dir('C:\\Users\\huutr\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\Ecommerce\\starter_code') {
                        bat 'mvn install'
                    }
                }
            }
        }
    }
}

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
                // Set up Java 8 environment
                tool 'Java 8'
                
                // Build the application
                sh 'mvn clean install'
            }
        }   
    }
}

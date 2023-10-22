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
        
        stage('Test') {
            steps {
                // Run unit tests using JUnit
                sh 'mvn test'
            }
        }
        
        stage('Deploy') {
            steps {
                // Create a directory on disk D
                sh 'mkdir -p /mnt/d/deployment'
                
                // Copy the built application to the deployment directory
                sh 'cp target/ecommerce.jar /mnt/d/deployment'
            }
        }
    }
}

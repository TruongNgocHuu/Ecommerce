pipeline {
    agent any    
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

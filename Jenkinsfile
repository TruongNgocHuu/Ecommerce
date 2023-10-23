pipeline {
    agent any 
       stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                git 'https://github.com/TruongNgocHuu/Ecommerce/starter_code'
            }
        }
        stage('Build') {
            steps {
                // Build the application
                    bat 'mvn install'
            }
        }   
    }
}

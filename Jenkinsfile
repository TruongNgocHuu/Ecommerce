pipeline {
    agent any 
       stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                git 'https://github.com/TruongNgocHuu/Ecommerce.git'
            }
        }
        stage('Build') {
           steps {
                sh 'mvn install'
            }       
        }
    }
}

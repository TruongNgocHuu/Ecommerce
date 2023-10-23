pipeline {
    agent any 
       stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                git 'https://github.com/TruongNgocHuu/Ecommerce'
                sh 'cd starter_code' 
            }
        }
        stage('Build') {
           steps {
                sh 'mvn install'
            }       
        }
    }
}

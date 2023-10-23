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
               bat 'cd starter_code'
               {
                   bat 'mvn install'
               }
                
            }       
        }
    }
}

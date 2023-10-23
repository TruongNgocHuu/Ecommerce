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
                    dir(path: 'starter_code', chdir: true) {
                        bat 'mvn install'
                    }
                }
            }
        }
    }
}

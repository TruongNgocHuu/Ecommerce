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
                script {
                    // Change directory to 'starter_code' without creating a temporary folder
                    def status = dir(path: 'starter_code', returnStatus: true) {
                        bat 'mvn install'
                    }
                    if (status != 0) {
                        error "Failed to change directory or execute Maven build"
                    }
                }
            }
        }
    }
}

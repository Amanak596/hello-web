pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t anan5250/hello-web:latest .'
            }
        }

        stage('Docker Run') {
            steps {
                sh '''
                docker rm -f hello-web-container 2>/dev/null || true

                docker run -d \
                  --name hello-web-container \
                  --add-host=host.docker.internal:host-gateway \
                  -p 8081:8081 \
                  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/studentdb \
                  -e SPRING_DATASOURCE_USERNAME=dockeruser \
                  -e SPRING_DATASOURCE_PASSWORD=docker@123 \
                  anan5250/hello-web:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'BUILD AND DEPLOYMENT SUCCESSFUL!'
        }

        failure {
            echo 'BUILD OR DEPLOYMENT FAILED!'
        }
    }
}

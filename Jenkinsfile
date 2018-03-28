node("master") {
    stage('Build Project') {
        echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        pwd
        sh 'gradlew clean build'
    }

    stage('Docker-compose up') {
        sh 'docker-compose up --build -d'
    }

    stage('Smoke tests') {
        echo "Running smoke-tests"
    }

    stage('Docker-compose down') {
        sh 'docker-compose down'
    }
}
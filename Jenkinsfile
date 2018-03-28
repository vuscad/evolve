node("master") {
    stage('Git checkout') {
        sh 'git clone https://github.com/vuscad/evolve.git'
        sh 'git checkout kotlin'
    }

    stage('Build Project') {
        sh './gradlew clean build'
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
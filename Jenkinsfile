node("master") {
    dir('/Users/Shared/Jenkins/Home/workspace/evolve@script') {
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
}
node("master") {

        stage('Checkout project') {
         sh 'cp -rf Users/Shared/Jenkins/Home/workspace/evolve@script/* Users/Shared/Jenkins/Home/workspace/evolve@tmp'
         sh 'cp -rf Users/Shared/Jenkins/Home/workspace/evolve@script/* Users/Shared/Jenkins/Home/workspace/evolve'
        }

        stage('Build Project') {
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
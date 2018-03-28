node("master") {
    stage('Build Project') {
        echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        echo "Workspace: ${env.WORKSPACE}"
        sh 'cp /Users/Shared/Jenkins/Home/workspace/evolve@script /Users/Shared/Jenkins/Home/workspace/evolve'
        echo "Running under dir: "
        sh 'pwd'
        echo "-----------------"
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
node("master") {
    stage('Build Project') {
        echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        sh 'ls'
        sh './gradlew clean build'
    }
}
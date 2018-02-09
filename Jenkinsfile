node("slave") {
    stage('Build Project') {
        echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        sh './gradlew clean build'
    }
}
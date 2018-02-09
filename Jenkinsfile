pipeline {
    agent any
    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"

    stages {
        stage 'Build Project'
        node{
          sh './gradlew clean build'
        }
    }
}
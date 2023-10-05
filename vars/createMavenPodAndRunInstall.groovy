podTemplate(
    label: 'maven-pod',
    containers: [
        containerTemplate(
            name: 'maven',
            image: 'maven:3.8.6-jdk-8-slim',
            ttyEnabled: true,
            command: 'cat'
        )
    ]
){
    node('maven-pod') {
        stage('Checkout') {
            checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: 'https://github.com/web3j/sample-project-maven.git']]]
        }

        stage('Build') {
            container('maven') {
                sh 'mvn clean install'
            }
        }
    }
}

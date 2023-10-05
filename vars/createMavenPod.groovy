def call(Closure body) {
    podTemplate(
        label: 'maven-pod',
        containers: [
            containerTemplate(
                name: 'maven',
                image: '3.8.6-jdk-8-slim',
                ttyEnabled: true,
                command: 'cat'
            )
        ]
    ) {
        node('maven-pod') {
            body()
        }
    }
}

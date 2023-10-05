def call(String image, String shCommand, Closure body = null) {
    def randomLabel = "pod-${UUID.randomUUID().toString()}"
    podTemplate(
        label: randomLabel,
        containers: [
            containerTemplate(
                name: 'container',
                image: image,
                ttyEnabled: true,
                command: 'cat'
            )
        ]
    ) {
        node(randomLabel) {
            body?.call()
            stage('Execute Command') {
                container('container') {
                    sh shCommand
                }
            }
        }
    }
}

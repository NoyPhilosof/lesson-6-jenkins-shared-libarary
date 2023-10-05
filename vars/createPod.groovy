def call(String image, String command = 'cat', Closure body) {
    def randomLabel = "pod-${UUID.randomUUID().toString()}"
    podTemplate(
        label: randomLabel,
        containers: [
            containerTemplate(
                name: 'maven',
                image: image,
                ttyEnabled: true,
                command: command
            )
        ]
    ) {
        node(randomLabel) {
            body.call()
        }
    }
}
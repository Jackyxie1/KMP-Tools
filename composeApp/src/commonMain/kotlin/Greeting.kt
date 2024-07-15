class Greeting {
    private val platform = currentPlatform

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
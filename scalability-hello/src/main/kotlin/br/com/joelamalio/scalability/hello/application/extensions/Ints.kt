package br.com.joelamalio.scalability.hello.application.extensions

private const val DEFAULT_PORT = 7000

fun Int.Companion.getPort(args: Array<String>): Int {
    val port = args.firstOrNull().let {
        it?.toIntOrNull()
    }

    return port ?: DEFAULT_PORT
}
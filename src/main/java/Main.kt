import kotlinx.coroutines.experimental.*

fun main(args: Array<String>) {
    println("Start")

    // Start a coroutine
    launch(CommonPool) {
        delay(1000)
        println("Hello")
    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")

    val deferred = (1..1000000).map { n ->
        async (CommonPool) {
            n.toLong()
        }
    }
    runBlocking {
        val sum = deferred.fold(0L) {
            prev, current -> prev + current.await()
        }
        println("Sum: $sum")
    }
}

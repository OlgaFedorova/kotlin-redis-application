package ofedorova.tweetdispatcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TweetDispatcherApplication

fun main(args: Array<String>) {
    runApplication<TweetDispatcherApplication>(*args)
}

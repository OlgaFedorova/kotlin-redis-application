package ofedorova.tweetapistub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TweetApiStubApplication

fun main(args: Array<String>) {
    runApplication<TweetApiStubApplication>(*args)
}

package ofedorova.trackedhashtagservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TrackedHashtagServiceApplication

fun main(args: Array<String>) {
    runApplication<TrackedHashtagServiceApplication>(*args)
}

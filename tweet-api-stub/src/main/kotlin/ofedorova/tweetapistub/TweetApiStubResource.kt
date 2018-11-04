package ofedorova.tweetapistub

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.*
import java.util.stream.Stream
import reactor.core.publisher.Flux
import java.util.Random
import java.time.Duration


@RestController
@RequestMapping("/api/tweet-api-stub")
class TweetApiStubResource {

    @PostMapping
    fun api(@RequestBody body:String): Flux<Tweet> {
        print(body)
        val r = Random()
        return Flux.fromStream(Stream.generate{r.nextInt()})
                .map { s -> Tweet(Integer.valueOf(s).toString(),
                        "Tweet${s}",
                        "today",
                        TwitterUser(Integer.valueOf(s).toString(), "user${s}"))}
                .delayElements(Duration.ofSeconds(1))
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class TwitterUser(val id: String, val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tweet(val id: String = "", val text: String = "",
                 @JsonProperty("created_at") val createdAt: String = "",
                 val user: TwitterUser = TwitterUser("", ""))


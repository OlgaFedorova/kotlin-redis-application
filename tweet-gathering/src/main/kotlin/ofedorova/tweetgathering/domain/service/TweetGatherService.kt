package ofedorova.tweetgathering.domain.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Service
class TweetGatherService(@Value("\${twitter.url}") private val url:String,
                         private val webClient: WebClient) {

    fun streamFrom(query: String): Flux<Tweet> {
        return this.webClient.mutate().baseUrl(url).build()
                .post()
                .body(BodyInserters.fromObject("track : ${query}"))
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve().bodyToFlux(Tweet::class.java)
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
data class TwitterUser(val id: String, val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tweet(val id: String = "", val text: String = "",
                 @JsonProperty("created_at") val createdAt: String = "",
                 val user: TwitterUser = TwitterUser("", ""))
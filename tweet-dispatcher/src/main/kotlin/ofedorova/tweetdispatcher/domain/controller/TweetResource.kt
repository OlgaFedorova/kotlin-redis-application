package ofedorova.tweetdispatcher.domain.controller

import ofedorova.tweetdispatcher.domain.service.Tweet
import ofedorova.tweetdispatcher.domain.service.TwitterDispatcher
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/tweets")
class TweetResource(private val dispatcher: TwitterDispatcher) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun tweets(): Flux<Tweet> {
        print("Hello")
        return dispatcher.dispatch()
    }

}
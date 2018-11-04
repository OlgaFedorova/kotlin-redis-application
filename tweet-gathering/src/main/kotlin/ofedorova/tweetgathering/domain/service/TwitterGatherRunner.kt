package ofedorova.tweetgathering.domain.service

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import ofedorova.tweetgathering.domain.TrackedHashTag
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

@Service
class TwitterGatherRunner(private val twitterGatherService: TweetGatherService,
                          private val rabbitTemplate: RabbitTemplate) {

    @RabbitListener(queues = ["twitter-track-hashtag"])
    fun receive(hashTag:TrackedHashTag) {
        val streamFrom = this.twitterGatherService.streamFrom(hashTag.hashTag).filter({
            return@filter it.id.isNotEmpty() && it.text.isNotEmpty() && it.createdAt.isNotEmpty()
        })
        val subscribe = streamFrom.subscribe({
            println(it.text)
            Mono.fromFuture(CompletableFuture.runAsync {
                this.rabbitTemplate.convertAndSend("twitter-exchange","track.${hashTag.queue}",it)
            })
        })
        Schedulers.elastic().schedule({ subscribe.dispose() },10L,TimeUnit.SECONDS)
    }

}
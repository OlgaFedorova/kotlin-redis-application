package ofedorova.trackedhashtagservice.domain.repository

import ofedorova.trackedhashtagservice.domain.TrackedHashTag
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TrackedHashTagRepository(private val redisTemplate: ReactiveRedisTemplate<String, String>) {

    fun save(trackedHashTag: TrackedHashTag): Mono<TrackedHashTag>? {
        return this.redisTemplate.opsForSet().add("hash-tags", "${trackedHashTag.hashTag}:${trackedHashTag.queue}").flatMap { Mono.just(trackedHashTag) }
    }

    fun findAll(): Flux<TrackedHashTag> {
        return this.redisTemplate.opsForSet().members("hash-tags").flatMap{ el ->
            val data = el.split(":")
            Flux.just(TrackedHashTag(hashTag = data[0], queue = data[1]))
        }
    }
}

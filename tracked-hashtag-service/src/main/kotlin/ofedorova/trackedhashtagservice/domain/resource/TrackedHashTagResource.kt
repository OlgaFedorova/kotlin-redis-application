package ofedorova.trackedhashtagservice.domain.resource

import ofedorova.trackedhashtagservice.domain.TrackedHashTag
import ofedorova.trackedhashtagservice.domain.service.TrackedHashTagService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tracked-hash-tag")
class TrackedHashTagResource(private val service: TrackedHashTagService) {
    @GetMapping
    fun all() = this.service.all()

    @PostMapping
    fun save(@RequestBody hashTag: TrackedHashTag) = this.service.save(hashTag)
}


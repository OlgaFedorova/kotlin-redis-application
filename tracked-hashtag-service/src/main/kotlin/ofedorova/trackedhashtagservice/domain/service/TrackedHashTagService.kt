package ofedorova.trackedhashtagservice.domain.service

import ofedorova.trackedhashtagservice.domain.TrackedHashTag
import ofedorova.trackedhashtagservice.domain.repository.TrackedHashTagRepository
import org.springframework.stereotype.Service

@Service
class TrackedHashTagService(private val repository: TrackedHashTagRepository) {
    fun save(hashTag: TrackedHashTag) = this.repository.save(hashTag)
    fun all() = this.repository.findAll()
}
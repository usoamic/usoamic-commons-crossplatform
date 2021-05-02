package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.extensions.orZero
import io.usoamic.commons.crossplatform.mappers.entity.NoteMapper
import io.usoamic.commons.crossplatform.models.repository.notes.AddNoteRequest
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.repositories.api.NotesRepository
import io.usoamic.usoamickt.core.Usoamic
import java.math.BigInteger
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : NotesRepository {
    private val address = usoamic.address

    override val numberOfPublicNotes: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamic.getNumberOfPublicNotes().orZero()
            }.addDebugDelay()
        }

    override val numberOfUserNotes: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamic.getNumberOfNotesByAuthor(address).orZero()
            }.addDebugDelay()
        }

    override fun getNote(refId: BigInteger): Single<NoteEntity> {
        return Single.fromCallable {
            usoamic.getNote(refId)
        }
            .map(NoteMapper(usoamic.address))
    }

    override fun getNoteForAccount(id: BigInteger): Single<NoteEntity> {
        return Single.fromCallable {
            usoamic.getNoteByAuthor(address, id)
        }
            .map(NoteMapper(usoamic.address))
    }

    override fun addPublicNote(data: AddNoteRequest): Single<String> {
        return Single.fromCallable {
            usoamic.addPublicNote(
                password = data.password,
                content = data.content,
                txSpeed = data.txSpeed
            )
        }
    }

    override fun addUnlistedNote(data: AddNoteRequest): Single<String> {
        return Single.fromCallable {
            usoamic.addUnlistedNote(
                password = data.password,
                content = data.content,
                txSpeed = data.txSpeed
            )
        }
    }
}
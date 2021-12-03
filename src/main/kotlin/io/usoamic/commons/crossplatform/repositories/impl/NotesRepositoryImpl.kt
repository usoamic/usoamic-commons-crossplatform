package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.extensions.orZero
import io.usoamic.commons.crossplatform.mappers.entity.NoteMapper
import io.usoamic.commons.crossplatform.models.repository.notes.AddNoteRequest
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.repositories.api.NotesRepository
import io.usoamic.usoamickt.account.api.UsoamicAccount
import io.usoamic.usoamickt.account.impl.corex.UsoamicAccountImpl
import java.math.BigInteger
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val usoamicAccount: UsoamicAccount,
) : NotesRepository {
    private val address = usoamicAccount.address

    override val numberOfPublicNotes: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamicAccount.getNumberOfPublicNotes().orZero()
            }.addDebugDelay()
        }

    override val numberOfUserNotes: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamicAccount.getNumberOfNotesByAuthor(address).orZero()
            }.addDebugDelay()
        }

    override fun getNote(refId: BigInteger): Single<NoteEntity> {
        return Single.fromCallable {
            usoamicAccount.getNote(refId)
        }
            .map(NoteMapper(address))
    }

    override fun getNoteForAccount(id: BigInteger): Single<NoteEntity> {
        return Single.fromCallable {
            usoamicAccount.getNoteByAuthor(address, id)
        }
            .map(NoteMapper(address))
    }

    override fun addPublicNote(data: AddNoteRequest): Single<String> {
        return Single.fromCallable {
            usoamicAccount.addPublicNote(
                password = data.password,
                content = data.content,
                txSpeed = data.txSpeed
            )
        }
    }

    override fun addUnlistedNote(data: AddNoteRequest): Single<String> {
        return Single.fromCallable {
            usoamicAccount.addUnlistedNote(
                password = data.password,
                content = data.content,
                txSpeed = data.txSpeed
            )
        }
    }
}
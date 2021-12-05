package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.repository.notes.AddNoteRequest
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import java.math.BigInteger

interface NotesRepository {
    val numberOfPublicNotes: Single<BigInteger>

    val numberOfUserNotes: Single<BigInteger>

    fun getNote(
        refId: BigInteger
    ): Single<NoteEntity>

    fun getNoteForAccount(
        id: BigInteger,
        address: String
    ): Single<NoteEntity>

    fun getNoteForAccount(
        id: BigInteger
    ): Single<NoteEntity>

    fun addPublicNote(
        data: AddNoteRequest
    ): Single<String>

    fun addUnlistedNote(
        data: AddNoteRequest
    ): Single<String>
}
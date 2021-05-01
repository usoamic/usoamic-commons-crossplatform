package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.mappers.local.mapEachToItem
import io.usoamic.commons.crossplatform.models.repository.notes.AddNoteRequest
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.NotesRepository
import io.usoamic.commons.crossplatform.repositories.api.ValidateRepository
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.usoamickt.enumcls.TxSpeed
import java.math.BigInteger
import javax.inject.Inject

class NotesUseCases @Inject constructor(
    private val mNotesRepository: NotesRepository,
    private val mValidateRepository: ValidateRepository,
    private val mDbRepository: DbRepository
) {
    fun addNote(password: String, content: String, noteType: String, txSpeed: String): Single<String> {
        return mValidateRepository.validatePassword(password)
            .andThen(mValidateRepository.validateContent(content))
            .andThen(
                Single.defer {
                    val data = AddNoteRequest(
                        password = password,
                        content = content,
                        txSpeed = TxSpeed.parseString(txSpeed)
                    )

                    when (NoteType.valueOf(noteType.toUpperCase())) {
                        NoteType.PUBLIC -> mNotesRepository.addPublicNote(data)
                        NoteType.UNLISTED -> mNotesRepository.addUnlistedNote(data)
                    }
                }
            )
    }

    fun getNotes(forceUpdate: Boolean): Single<List<NoteItem>> {
        return if (forceUpdate) {
            getNotesFromNetwork()
        } else {
            getNotesFromCache()
        }
    }

    private fun getNotesFromCache(): Single<List<NoteItem>> {
        val items = mDbRepository.getNotes()
        return if (items.isEmpty()) {
            getNotesFromNetwork()
        } else {
            Single.just(items.mapEachToItem())
        }
    }

    private fun getNotesFromNetwork(): Single<List<NoteItem>> {
        return mNotesRepository.numberOfUserNotes
            .map { size ->
                val items = mutableListOf<NoteEntity>()
                var i = BigInteger.ZERO

                while (i < size) {
                    val note = mNotesRepository.getNoteForAccount(i).blockingGet()
                    items.add(note)
                    i++
                }
                items
            }
            .map { items ->
                items.forEach {
                    mDbRepository.addNote(it)
                }
                items.mapEachToItem()
            }
    }
}
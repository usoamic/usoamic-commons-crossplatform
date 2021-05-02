package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.exceptions.NoteNotFoundThrowable
import io.usoamic.commons.crossplatform.mappers.local.mapEachToItem
import io.usoamic.commons.crossplatform.mappers.local.toItem
import io.usoamic.commons.crossplatform.models.repository.notes.AddNoteRequest
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.NotesRepository
import io.usoamic.commons.crossplatform.repositories.api.ValidateRepository
import io.usoamic.commons.crossplatform.utils.QueryDestination
import io.usoamic.commons.crossplatform.utils.logQuery
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

    fun getNote(refId: Long, forceUpdate: Boolean): Single<NoteItem> {
        return if (forceUpdate) {
            getNoteFromNetwork(refId)
        } else {
            getNoteFromCache(refId)
        }
    }

    fun getNoteForAccount(id: Long, forceUpdate: Boolean): Single<NoteItem> {
        return if (forceUpdate) {
            getNoteForAccountFromNetwork(id)
        } else {
            getNoteForAccountFromCache(id)
        }
    }

    fun getNotes(forceUpdate: Boolean): Single<List<NoteItem>> {
        return if (forceUpdate) {
            getNotesFromNetwork()
        } else {
            getNotesFromCache()
        }.map { items ->
            items.sortedByDescending {
                it.timestamp
            }
        }
    }

    private fun getNotesFromCache(): Single<List<NoteItem>> {
        val items = mDbRepository.getNotes()
        return if (items.isEmpty()) {
            getNotesFromNetwork()
        } else {
            logQuery(QueryDestination.CACHE, "getNotes")
            Single.just(items.mapEachToItem())
        }
    }

    private fun getNotesFromNetwork(): Single<List<NoteItem>> {
        logQuery(QueryDestination.NETWORK, "getNotes")
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
                    mDbRepository.addNoteForAccount(it)
                }
                items.mapEachToItem()
            }
    }

    private fun getNoteFromCache(refId: Long): Single<NoteItem> {
        return mDbRepository.getNote(refId)?.let {
            logQuery(QueryDestination.CACHE, "getNote")
            Single.just(it.toItem())
        } ?: getNoteFromNetwork(refId)
    }

    private fun getNoteFromNetwork(refId: Long): Single<NoteItem> {
        logQuery(QueryDestination.NETWORK, "getNote")
        return mNotesRepository.numberOfPublicNotes
            .map { it.toLong() }
            .map { amount ->
                if (amount <= refId) {
                    throw NoteNotFoundThrowable(
                        id = refId,
                        forAuthor = false
                    )
                }
                amount
            }
            .flatMap {
                mNotesRepository.getNote(refId.toBigInteger())
                    .map { note ->
                        mDbRepository.addNote(note)
                        note.toItem()
                    }
            }
    }

    private fun getNoteForAccountFromCache(id: Long): Single<NoteItem> {
        return mDbRepository.getNoteForAccount(id)?.let {
            logQuery(QueryDestination.CACHE, "getNoteForAccount")
            Single.just(it.toItem())
        } ?: getNoteForAccountFromNetwork(id)
    }

    private fun getNoteForAccountFromNetwork(id: Long): Single<NoteItem> {
        logQuery(QueryDestination.NETWORK, "getNoteForAccount")
        return mNotesRepository.numberOfUserNotes
            .map { it.toLong() }
            .map { amount ->
                if (amount <= id) {
                    throw NoteNotFoundThrowable(
                        id = id,
                        forAuthor = true
                    )
                }
                amount
            }
            .flatMap {
                mNotesRepository.getNoteForAccount(id.toBigInteger())
                    .map { note ->
                        mDbRepository.addNote(note)
                        note.toItem()
                    }
            }
    }

    private fun logQuery(destination: QueryDestination, msg: String) {
        logQuery(
            tag = TAG, 
            destination = destination,
            msg = msg
        )
    }

    companion object {
        val TAG: String get() = this::class.java.simpleName
    }
}
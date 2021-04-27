package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.mappers.local.mapEachToItem
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.NotesRepository
import java.math.BigInteger
import javax.inject.Inject

class NotesUseCases @Inject constructor(
    private val mNotesRepository: NotesRepository,
    private val mDbRepository: DbRepository
) {
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
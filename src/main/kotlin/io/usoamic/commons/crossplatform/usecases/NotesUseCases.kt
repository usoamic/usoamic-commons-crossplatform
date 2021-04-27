package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItemModel
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.NotesRepository
import java.math.BigInteger
import javax.inject.Inject

class NotesUseCases @Inject constructor(
    private val mNotesRepository: NotesRepository,
    private val mDbRepository: DbRepository
) {
    fun getNotes(forceUpdate: Boolean): Single<List<NoteItemModel>> {
        return if (forceUpdate) {
            getNotesFromNetwork()
        } else {
            getNotesFromCache()
        }
    }

    private fun getNotesFromCache(): Single<List<NoteItemModel>> {
        val items = mDbRepository.getNotes()
        return if (items.isEmpty()) {
            getNotesFromNetwork()
        } else {
            Single.just(items)
        }
    }

    private fun getNotesFromNetwork(): Single<List<NoteItemModel>> {
        return mNotesRepository.numberOfUserNotes
            .map { size ->
                val items = mutableListOf<NoteItemModel>()
                var i = BigInteger.ZERO

                while (i < size) {
                    val note = mNotesRepository.getNoteForAccount(i).blockingGet()
                    items.add(note)
                    i++
                }

                items.toList()
            }
            .map { items ->
                items.forEach {
                    mDbRepository.addNote(it)
                }
                items
            }
    }
}
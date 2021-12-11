package io.usoamic.commons.crossplatform.mappers.entity

import io.reactivex.functions.Function
import io.usoamic.commons.crossplatform.exceptions.NoteNotFoundThrowable
import io.usoamic.commons.crossplatform.extensions.toCleanedHexPrefixString
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.usoamickt.model.Note

internal class NoteMapper(
    private val address: String,
    private val forAuthor: Boolean = false
) : Function<Note, NoteEntity> {
    override fun apply(note: Note): NoteEntity {
        if (!note.isExist) {
            throw NoteNotFoundThrowable(
                id = note.noteId.toLong(),
                forAuthor = forAuthor
            )
        }
        return NoteEntity(
            noteId = note.noteId,
            noteType = note.type,
            noteRefId = note.noteRefId,
            content = note.content,
            author = note.author,
            timestamp = note.timestamp,
            isAuthor = (note.author.toCleanedHexPrefixString() == address.toCleanedHexPrefixString())
        )
    }
}
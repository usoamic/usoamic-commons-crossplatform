package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.usoamickt.enumcls.NoteType

fun NoteEntity.toItem(): NoteItem {
    return when(noteType) {
        NoteType.PUBLIC -> {
            NoteItem.Public(
                id = noteRefId.toLong(),
                content = content,
                author = author,
                timestamp = timestamp.toLong(),
                isAuthor = isAuthor
            )
        }
        NoteType.UNLISTED -> {
            NoteItem.Unlisted(
                id = noteId.toLong(),
                content = content,
                author = author,
                timestamp = timestamp.toLong(),
                isAuthor = isAuthor
            )
        }
    }
}

fun List<NoteEntity>.mapEachToItem(): List<NoteItem> = map {
    it.toItem()
}
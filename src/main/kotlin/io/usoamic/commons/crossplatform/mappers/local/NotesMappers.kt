package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem

fun NoteEntity.toItem(): NoteItem {
    return NoteItem(
        id = noteId.toLong(),
        type = noteType,
        refId = noteRefId.toLong(),
        content = content,
        author = author,
        timestamp = timestamp.toLong(),
        isAuthor = isAuthor
    )
}

fun List<NoteEntity>.mapEachToItem(): List<NoteItem> = map {
    it.toItem()
}
package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem

fun NoteEntity.toItem(): NoteItem {
    return NoteItem(
        id = noteId.toLong(),
        visibility = visibility,
        refId = noteRefId.toLong(),
        content = content,
        author = author,
        timestamp = timestamp.toLong()
    )
}

fun List<NoteEntity>.mapEachToItem(): List<NoteItem> = map {
    it.toItem()
}
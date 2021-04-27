package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItemModel

fun NoteEntity.toItem(): NoteItemModel {
    return NoteItemModel(
        id = noteId.toLong(),
        visibility = visibility,
        refId = noteRefId.toLong(),
        content = content,
        author = author,
        timestamp = timestamp.toLong()
    )
}
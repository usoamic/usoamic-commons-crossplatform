package io.usoamic.commons.crossplatform.mappers.entity

import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItemModel
import io.usoamic.usoamickt.model.Note

internal fun Note.toEntity(): NoteItemModel = NoteItemModel(
    id = noteId.toLong(),
    visibility = visibility,
    refId = noteRefId.toLong(),
    content = content,
    author = author,
    timestamp = timestamp.toLong()
)
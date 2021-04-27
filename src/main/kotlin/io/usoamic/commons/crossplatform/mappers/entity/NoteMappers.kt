package io.usoamic.commons.crossplatform.mappers.entity

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.usoamickt.model.Note

internal fun Note.toEntity(): NoteEntity = NoteEntity(
    noteId = noteId,
    visibility = visibility,
    noteRefId = noteRefId,
    content = content,
    author = author,
    timestamp = timestamp
)
package io.usoamic.commons.crossplatform.models.usecases.notes

import io.usoamic.usoamickt.enumcls.NoteType

data class NoteItem(
    val id: Long,
    val type: NoteType,
    val refId: Long,
    val content: String,
    val author: String,
    val timestamp: Long,
    val isAuthor: Boolean
)


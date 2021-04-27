package io.usoamic.commons.crossplatform.models.usecases.notes

import io.usoamic.usoamickt.enumcls.NoteVisibility

data class NoteItem(
    val id: Long,
    val visibility: NoteVisibility,
    val refId: Long,
    val content: String,
    val author: String,
    val timestamp: Long
)


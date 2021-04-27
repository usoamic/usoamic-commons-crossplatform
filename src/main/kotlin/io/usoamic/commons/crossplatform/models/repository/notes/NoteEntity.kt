package io.usoamic.commons.crossplatform.models.repository.notes

import io.usoamic.usoamickt.enumcls.NoteVisibility
import java.math.BigInteger

data class NoteEntity(
    val noteId: BigInteger,
    val visibility: NoteVisibility,
    val noteRefId: BigInteger,
    val content: String,
    val author: String,
    val timestamp: BigInteger
)


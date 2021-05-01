package io.usoamic.commons.crossplatform.models.repository.notes

import io.usoamic.usoamickt.enumcls.NoteType
import java.math.BigInteger

data class NoteEntity(
    val noteId: BigInteger,
    val noteType: NoteType,
    val noteRefId: BigInteger,
    val content: String,
    val author: String,
    val timestamp: BigInteger
)


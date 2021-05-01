package io.usoamic.commons.crossplatform.models.repository.notes

import io.usoamic.usoamickt.enumcls.TxSpeed

data class AddNoteRequest(
    val password: String,
    val content: String,
    val txSpeed: TxSpeed = TxSpeed.Auto
)
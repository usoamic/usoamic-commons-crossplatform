package io.usoamic.commons.crossplatform.models.usecases.history

import io.usoamic.usoamickt.util.Coin
import java.math.BigInteger

data class TransactionItem(
    val type: TransactionType,
    val txId: Long,
    val from: String,
    val to: String,
    val value: Coin,
    val timestamp: Long
)



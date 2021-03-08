package io.usoamic.commons.crossplatform.models.history

import io.usoamic.usoamickt.model.Transaction
import io.usoamic.usoamickt.util.Coin

data class TransactionItem(
    val type: TransactionType,
    val txId: Long,
    val from: String,
    val to: String,
    val value: Coin,
    val timestamp: Long
)

enum class TransactionType {
    DEPOSIT,
    WITHDRAW
}

fun Transaction.toDomain(owner: String): TransactionItem = TransactionItem(
    type = when (owner) {
        from -> {
            TransactionType.WITHDRAW
        }
        to -> {
            TransactionType.DEPOSIT
        }
        else -> throw IllegalArgumentException()
    },
    txId = txId.toLong(),
    from = from,
    to = to,
    value = Coin.fromCoin(value),
    timestamp = timestamp.toLong()
)
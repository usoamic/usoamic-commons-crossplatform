package io.usoamic.commons.crossplatform.mappers.entity

import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionTypeEntity
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.usoamickt.util.Coin

internal fun Transaction.toEntity(owner: String): TransactionEntity {
    return TransactionEntity(
        type = when (owner) {
            from -> {
                TransactionTypeEntity.WITHDRAW
            }
            to -> {
                TransactionTypeEntity.DEPOSIT
            }
            else -> throw IllegalArgumentException()
        },
        txId = txId,
        from = from,
        to = to,
        value = Coin.fromCoin(value),
        timestamp = timestamp
    )
}
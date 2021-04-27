package io.usoamic.commons.crossplatform.mappers.local

import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionTypeEntity
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionItem
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionType

fun TransactionEntity.toItem(): TransactionItem {
    return TransactionItem(
        type = when (type) {
            TransactionTypeEntity.DEPOSIT -> TransactionType.DEPOSIT
            TransactionTypeEntity.WITHDRAW -> TransactionType.WITHDRAW
        },
        txId = txId.toLong(),
        from = from,
        to = to,
        value = value,
        timestamp = timestamp.toLong()
    )
}

fun List<TransactionEntity>.mapEachToItem(): List<TransactionItem> = map {
    it.toItem()
}
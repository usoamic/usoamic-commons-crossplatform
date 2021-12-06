package io.usoamic.commons.crossplatform.mappers.entity

import io.reactivex.functions.Function
import io.usoamic.commons.crossplatform.exceptions.TransactionNotFoundThrowable
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionTypeEntity
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.usoamickt.util.Coin

internal class TransactionMapper(
    private val owner: String
) : Function<Transaction, TransactionEntity> {
    override fun apply(transaction: Transaction): TransactionEntity {
        val txId = transaction.txId
        val from = transaction.from
        val to = transaction.to

        if (!transaction.isExist) {
            throw TransactionNotFoundThrowable(
                id = txId.toLong(),
                address = owner
            )
        }

        return TransactionEntity(
            type = getTransaction(
                from = from,
                to = to
            ),
            txId = txId,
            from = from,
            to = to,
            value = Coin.fromCoin(transaction.value),
            timestamp = transaction.timestamp
        )
    }

    private fun getTransaction(
        from: String,
        to: String
    ): TransactionTypeEntity {
        return when (owner.toLowerCase()) {
            from.toLowerCase() -> {
                TransactionTypeEntity.WITHDRAW
            }
            to.toLowerCase() -> {
                TransactionTypeEntity.DEPOSIT
            }
            else -> throw IllegalArgumentException()
        }
    }
}
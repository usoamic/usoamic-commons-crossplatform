package io.usoamic.commons.crossplatform.models.repository.history

import io.usoamic.commons.crossplatform.models.usecases.history.TransactionType
import io.usoamic.usoamickt.util.Coin
import java.math.BigInteger

data class TransactionEntity(
    val type: TransactionTypeEntity,
    val txId: BigInteger,
    val from: String,
    val to: String,
    val value: Coin,
    val timestamp: BigInteger
)



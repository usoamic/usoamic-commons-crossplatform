package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.history.TransactionInfoItem
import io.usoamic.commons.crossplatform.models.withdraw.WithdrawInfo
import io.usoamic.usoamickt.model.Transaction
import java.math.BigDecimal
import java.math.BigInteger

interface TokenRepository {
    val usoBalance: Single<BigDecimal>
    val usoSupply: Single<BigDecimal>
    val numberOfUserTransactions: Single<BigInteger>
    fun getTransaction(
        txId: BigInteger
    ): Single<Transaction>

    fun getTransactionForAccount(
        txId: BigInteger
    ): Single<TransactionInfoItem>

    fun withdraw(
        data: WithdrawInfo
    ): Single<String>
}
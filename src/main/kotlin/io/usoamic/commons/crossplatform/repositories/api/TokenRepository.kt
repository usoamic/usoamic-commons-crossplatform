package io.usoamic.commons.crossplatform.repositories.api

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.withdraw.WithdrawRequest
import io.usoamic.usoamickt.model.Transaction
import java.math.BigDecimal
import java.math.BigInteger

interface TokenRepository {
    val usoBalance: Single<BigDecimal>

    val usoSupply: Single<BigDecimal>

    val usoVersion: Single<String>

    val numberOfUserTransactions: Single<BigInteger>

    fun getBalance(address: String): Single<BigDecimal>

    fun getTransaction(
        txId: BigInteger
    ): Single<Transaction>

    fun getTransactionByAddress(
        txId: BigInteger
    ): Single<TransactionEntity>

    fun getTransactionByAddress(
        owner: String,
        txId: BigInteger
    ): Single<TransactionEntity>

    fun withdraw(
        data: WithdrawRequest
    ): Single<String>
}
package io.usoamic.wallet.commons.repositories

import io.reactivex.Single
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.wallet.commons.models.history.TransactionItem
import io.usoamic.wallet.commons.models.withdraw.WithdrawData
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
    ): Single<TransactionItem>

    fun withdraw(
        data: WithdrawData
    ): Single<String>
}
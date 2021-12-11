package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.exceptions.ContractNullThrowable
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.extensions.orZero
import io.usoamic.commons.crossplatform.mappers.entity.TransactionMapper
import io.usoamic.commons.crossplatform.mappers.entity.toCoin
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.withdraw.WithdrawRequest
import io.usoamic.commons.crossplatform.repositories.api.TokenRepository
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.usoamickt.util.Coin
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : TokenRepository {
    private val address = usoamic.address

    override val usoBalance: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamic.getUsoBalance().toCoin()
            }
                .addDebugDelay()
        }

    override val usoSupply: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamic.getSupply().toCoin()
            }
                .addDebugDelay()
        }

    override val usoVersion: Single<String>
        get() = Single.fromCallable {
            usoamic.getVersion() ?: throw ContractNullThrowable("usoVersion")
        }.addDebugDelay()

    override val numberOfUserTransactions: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamic.getNumberOfTransactionsByAddress(address).orZero()
            }.addDebugDelay()
        }

    override fun getBalance(address: String): Single<BigDecimal> {
        return Single.fromCallable {
            usoamic.balanceOf(address).toCoin()
        }.addDebugDelay()
    }

    override fun getTransaction(txId: BigInteger): Single<Transaction> {
        return Single.fromCallable {
            usoamic.getTransaction(txId)
        }.addDebugDelay()
    }

    override fun getTransactionForAccount(txId: BigInteger): Single<TransactionEntity> {
        return getTransactionByAddress(
            owner = address,
            txId = txId
        )
    }

    override fun getTransactionByAddress(owner: String, txId: BigInteger): Single<TransactionEntity> {
        return Single.fromCallable {
            usoamic.getTransactionByAddress(owner, txId)
        }.map(TransactionMapper(owner))
            .addDebugDelay()
    }

    override fun withdraw(data: WithdrawRequest): Single<String> {
        return Single.fromCallable {
            val value = Coin.fromCoin(data.value).toSat()
            usoamic.transferUso(
                password = data.password,
                to = data.to,
                value = value,
                txSpeed = data.txSpeed
            )
        }
            .addDebugDelay()
    }


}
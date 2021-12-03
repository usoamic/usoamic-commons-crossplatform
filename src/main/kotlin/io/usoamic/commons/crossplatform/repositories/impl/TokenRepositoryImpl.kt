package io.usoamic.commons.crossplatform.repositories.impl

import io.reactivex.Single
import io.usoamic.commons.crossplatform.exceptions.ContractNullThrowable
import io.usoamic.commons.crossplatform.extensions.addDebugDelay
import io.usoamic.commons.crossplatform.extensions.orZero
import io.usoamic.commons.crossplatform.mappers.entity.toEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.withdraw.WithdrawRequest
import io.usoamic.commons.crossplatform.repositories.api.TokenRepository
import io.usoamic.usoamickt.account.api.UsoamicAccount
import io.usoamic.usoamickt.account.impl.corex.UsoamicAccountImpl
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.usoamickt.util.Coin
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val usoamicAccount: UsoamicAccount
) : TokenRepository {
    private val address = usoamicAccount.address

    override val usoBalance: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamicAccount.getUsoBalance().toCoin()
            }
                .addDebugDelay()
        }

    override val usoSupply: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamicAccount.getSupply().toCoin()
            }
                .addDebugDelay()
        }

    override val numberOfUserTransactions: Single<BigInteger>
        get() {
            return Single.fromCallable {
                usoamicAccount.getNumberOfTransactionsByAddress(address).orZero()
            }.addDebugDelay()
        }

    override fun getTransaction(txId: BigInteger): Single<Transaction> {
        return Single.fromCallable {
            usoamicAccount.getTransaction(txId)
        }.addDebugDelay()
    }

    override fun getTransactionForAccount(txId: BigInteger): Single<TransactionEntity> {
        return Single.fromCallable {
            usoamicAccount.getTransactionByAddress(address, txId)
        }.map {
            it.toEntity(address)
        }
        // .addDebugDelay()
    }

    override fun withdraw(data: WithdrawRequest): Single<String> {
        return Single.fromCallable {
            val value = Coin.fromCoin(data.value).toSat()
            usoamicAccount.transferUso(
                password = data.password,
                to = data.to,
                value = value,
                txSpeed = data.txSpeed
            )
        }
            .addDebugDelay()
    }

    private fun BigInteger?.toCoin(): BigDecimal {
        return this?.let {
            Coin.fromSat(it).toBigDecimal()
        } ?: throw ContractNullThrowable("mapToCoin()")
    }
}
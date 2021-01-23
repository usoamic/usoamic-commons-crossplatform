package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.wallet.commons.models.history.TransactionItem
import io.usoamic.wallet.commons.repositories.api.DbRepository
import io.usoamic.wallet.commons.repositories.api.TokenRepository
import java.math.BigInteger
import javax.inject.Inject

class HistoryUseCases @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mDbRepository: DbRepository
) {
    fun getTransactions(forceUpdate: Boolean): Single<List<TransactionItem>> {
        return if(forceUpdate) {
            getTransactionsFromNetwork()
        }
        else {
            getTransactionsFromCache()
        }
            .map { items ->
                items.sortedByDescending {
                    it.timestamp
                }
            }
    }

    private fun getTransactionsFromCache(): Single<List<TransactionItem>> {
        val items = mDbRepository.getTransactions()
        return if(items.isEmpty()) {
            getTransactionsFromNetwork()
        }
        else {
            Single.just(items)
        }
    }

    private fun getTransactionsFromNetwork(): Single<List<TransactionItem>> {
        return mTokenRepository.numberOfUserTransactions
            .map { size ->
                val items = mutableListOf<TransactionItem>()
                var i = BigInteger.ZERO
                while (i < size) {
                    val tx = mTokenRepository.getTransactionForAccount(i).blockingGet()
                    items.add(tx)
                    i++
                }
                items.toList()
            }
            .map { items ->
                items.forEach {
                    mDbRepository.addTransactionItem(it)
                }
                items
            }
    }
}
package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.history.TransactionInfoItem
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.TokenRepository

import java.math.BigInteger
import javax.inject.Inject

class HistoryUseCases @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mDbRepository: DbRepository
) {
    fun getTransactions(forceUpdate: Boolean): Single<List<TransactionInfoItem>> {
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

    private fun getTransactionsFromCache(): Single<List<TransactionInfoItem>> {
        val items = mDbRepository.getTransactions()
        return if(items.isEmpty()) {
            getTransactionsFromNetwork()
        }
        else {
            Single.just(items)
        }
    }

    private fun getTransactionsFromNetwork(): Single<List<TransactionInfoItem>> {
        return mTokenRepository.numberOfUserTransactions
            .map { size ->
                val items = mutableListOf<TransactionInfoItem>()
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
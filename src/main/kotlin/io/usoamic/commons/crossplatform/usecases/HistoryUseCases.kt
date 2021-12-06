package io.usoamic.commons.crossplatform.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.mappers.local.mapEachToItem
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionItem
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.TokenRepository

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
        val entities = mDbRepository.getTransactions()
        return if(entities.isEmpty()) {
            getTransactionsFromNetwork()
        }
        else {
            Single.just(entities.mapEachToItem())
        }
    }

    private fun getTransactionsFromNetwork(): Single<List<TransactionItem>> {
        return mTokenRepository.numberOfUserTransactions
            .map { size ->
                val items = mutableListOf<TransactionEntity>()
                var i = BigInteger.ZERO
                while (i < size) {
                    val tx = mTokenRepository.getTransactionForAccount(i).blockingGet()
                    items.add(tx)
                    i++
                }
                items
            }
            .map { items ->
                items.forEach {
                    mDbRepository.addTransactionItem(it)
                }
                items.mapEachToItem()
            }
    }
}
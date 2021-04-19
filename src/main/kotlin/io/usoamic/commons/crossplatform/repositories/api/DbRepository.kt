package io.usoamic.commons.crossplatform.repositories.api

import io.usoamic.commons.crossplatform.models.dashboard.DashboardInfo
import io.usoamic.commons.crossplatform.models.history.TransactionItem

interface DbRepository {
    fun updateDashboardInfo(data: DashboardInfo)
    fun addTransactionItem(data: TransactionItem)

    fun getDashboardInfo(): DashboardInfo?
    fun getTransactions(): List<TransactionItem>

    fun removeAll()
}
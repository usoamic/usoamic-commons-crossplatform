package io.usoamic.commons.crossplatform.repositories.api

import io.usoamic.commons.crossplatform.models.dashboard.DashboardInfo
import io.usoamic.commons.crossplatform.models.history.TransactionInfoItem

interface DbRepository {
    fun updateDashboardInfo(data: DashboardInfo)
    fun addTransactionItem(data: TransactionInfoItem)

    fun getDashboardInfo(): DashboardInfo?
    fun getTransactions(): List<TransactionInfoItem>

    fun removeAll()
}
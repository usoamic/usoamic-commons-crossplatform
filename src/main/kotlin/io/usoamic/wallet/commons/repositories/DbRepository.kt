package io.usoamic.wallet.commons.repositories

import io.usoamic.wallet.commons.models.dashboard.DashboardInfo
import io.usoamic.wallet.commons.models.history.TransactionItem

interface DbRepository {
    fun updateDashboardInfo(data: DashboardInfo)
    fun addTransactionItem(data: TransactionItem)

    fun getDashboardInfo(): DashboardInfo?
    fun getTransactions(): List<TransactionItem>
}
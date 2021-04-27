package io.usoamic.commons.crossplatform.repositories.api

import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItemModel

interface DbRepository {
    fun updateDashboardInfo(data: DashboardEntity)
    fun addTransactionItem(data: TransactionEntity)

    fun getDashboardInfo(): DashboardEntity?
    fun getTransactions(): List<TransactionEntity>

    fun addNote(data: NoteItemModel)
    fun getNotes(): List<NoteItemModel>

    fun removeAll()
}
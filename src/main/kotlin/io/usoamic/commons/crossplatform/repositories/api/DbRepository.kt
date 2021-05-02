package io.usoamic.commons.crossplatform.repositories.api

import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity

interface DbRepository {
    fun updateDashboardInfo(data: DashboardEntity)
    fun addTransactionItem(data: TransactionEntity)

    fun getDashboardInfo(): DashboardEntity?
    fun getTransactions(): List<TransactionEntity>

    fun addNote(data: NoteEntity)
    fun addNoteForAccount(data: NoteEntity)

    fun getNote(refId: Long): NoteEntity?
    fun getNoteForAccount(id: Long): NoteEntity?

    fun getNotes(): List<NoteEntity>

    fun removeAll()
}
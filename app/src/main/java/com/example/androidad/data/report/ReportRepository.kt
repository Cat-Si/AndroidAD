package com.example.androidad.data.report

import com.example.androidad.data.DatabaseResult
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow

interface ReportRepo {
    fun delete(report: Report)

    fun add(report: Report)

    fun edit(report: Report)

    suspend fun getAll(): Flow<DatabaseResult<List<Report?>>>

    fun updateUserListener(userToListenTo: DatabaseReference)
}

class ReportRepository(private val reportDAO: ReportDAO) : ReportRepo {
    override fun delete(report: Report) = reportDAO.delete(report)

    override fun add(report: Report) {
        reportDAO.insert(report)
    }

    override fun edit(report: Report) {
        reportDAO.update(report)
    }

    override suspend fun getAll(): Flow<DatabaseResult<List<Report?>>> {
        return reportDAO.getReports()
    }

    override fun updateUserListener(userToListenTo: DatabaseReference) {
        reportDAO.updateUserListener(userToListenTo)
    }
}
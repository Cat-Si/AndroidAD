package com.example.androidad.data.report

import com.example.androidad.data.DatabaseResult
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow

interface ReportRepo {
    fun delete(report: Report, uid: String)

    fun add(report: Report, reportUUID: String)

    fun edit(report: Report, reportUUID: String)

    suspend fun getAll(): Flow<DatabaseResult<List<Report?>>>

    fun updateUserListener(userToListenTo: DatabaseReference)
}

class ReportRepository(private val reportDAO: ReportDAO) : ReportRepo {
    override fun delete(report: Report, uid: String) = reportDAO.delete(report, uid)

    override fun add(report: Report, reportUUID: String) {
        reportDAO.addNewReport(report, reportUUID)
    }

    override fun edit(report: Report, reportUUID: String) {
        reportDAO.update(report, reportUUID)
    }

    override suspend fun getAll(): Flow<DatabaseResult<List<Report?>>> {
        return reportDAO.getReports()
    }

    override fun updateUserListener(userToListenTo: DatabaseReference) {
        reportDAO.updateUserListener(userToListenTo)
    }
}
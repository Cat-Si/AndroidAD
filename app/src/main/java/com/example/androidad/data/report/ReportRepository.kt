package com.example.androidad.data.report

import com.example.androidad.data.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface ReportRepo{
    fun delete(report: Report, uid: String): Task<Void>

    fun add(report: Report, reportUUID: String)

    fun edit(report: Report, reportUUID: String)

    suspend fun getAll(reportUUID: String): Flow<DatabaseResult<List<Report?>>>
}

class ReportRepository(private val reportDAO: ReportDAO) : ReportRepo {
    override fun delete(report: Report, uid: String) = reportDAO.delete(report, uid)

    override fun add(report: Report, reportUUID: String) { reportDAO.insert(report, reportUUID)}

    override fun edit(report: Report, reportUUID: String) { reportDAO.update(report, reportUUID)}

    override suspend fun getAll(reportUUID: String): Flow<DatabaseResult<List<Report?>>> {
        return reportDAO.getReports(reportUUID)
    }
}
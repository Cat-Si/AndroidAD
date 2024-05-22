package com.example.androidad.data.report

import com.example.androidad.data.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class ReportDAO(private val database: DatabaseReference) {

    suspend fun getReports(reportUUID: String) : Flow<DatabaseResult<List<Report?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.child(reportUUID).keepSynced(true)

        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reports = ArrayList<Report>()
                for (childSnapshot in snapshot.children) {
                    val report = childSnapshot.getValue(Report::class.java)
                    report!!.id = childSnapshot.key.toString()
                    reports.add(report)
                }
                trySend(DatabaseResult.Success(reports))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(DatabaseResult.Error(Throwable(error.message)))
            }
        }
        database.child(reportUUID).addValueEventListener(event)
        awaitClose { close() }
    }

    fun insert(newReport: Report, userAuthUUID: String) = database.child(userAuthUUID).child(UUID.randomUUID().toString()).setValue(newReport)

    fun update(editReport: Report, userAuthUUID: String) {
        val reportID = editReport.id.toString() //retrieved for sub folder key
        editReport.id = String() //Clear so not saved inside folder
        database.child(userAuthUUID).child(reportID).setValue(editReport)
    }

    fun delete(report: Report, uid: String) = database.child(uid).child(report.id.toString()).removeValue()
}

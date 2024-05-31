package com.example.androidad.data.report

import android.content.ContentValues.TAG
import android.util.Log
import com.example.androidad.data.DatabaseResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class ReportDAO(
    private val database: DatabaseReference,
    private val contactRoot: DatabaseReference
) {

    suspend fun getReports(reportUUID: String): Flow<DatabaseResult<List<Report?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.child(reportUUID).keepSynced(true)

        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reports = ArrayList<Report>()
                for (childSnapshot in snapshot.children) {
                    val report = childSnapshot.getValue(Report::class.java)
                    report!!.uid = childSnapshot.key.toString()
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

    fun insert(newReport: Report, userAuthUUID: String) =
        database.child(userAuthUUID).child(UUID.randomUUID().toString()).setValue(newReport)

    fun update(editReport: Report, userAuthUUID: String) {
        val reportID = editReport.uid.toString() //retrieved for sub folder key
        editReport.uid = String() //Clear so not saved inside folder
        val key = database.child(userAuthUUID).child(reportID).push().key
        if (key == null) {
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }


        database.child(userAuthUUID).child(reportID).setValue(editReport)
        contactRoot.child(userAuthUUID).child("reports").setValue(editReport)
    }
//        database.child(userAuthUUID).child(reportID).setValue(editReport)


    fun delete(report: Report, userAuthUUID: String) {
        database.child(userAuthUUID).child(report.uid.toString()).removeValue()
        contactRoot.child(userAuthUUID).child("reports").child(report.uid.toString()).removeValue()
    }


    fun addNewReport(
        report: Report, userAuthUUID: String,
    ) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        val key = database.child(userAuthUUID).push().key
        if (key == null) {
            Log.w(TAG, "Couldn't get push key for posts")
            return
        }

        val reportValues = report.toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/$userAuthUUID/$key" to reportValues,
        )
        val contactChildUpdates = hashMapOf<String, Any>(
            "/$userAuthUUID/reports/$key" to reportValues,

            )

        database.updateChildren(childUpdates)
        contactRoot.updateChildren(contactChildUpdates)
    }
}


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
    private var reportRoot: DatabaseReference,
) {
    private var event: ValueEventListener? = null;

    fun updateUserListener(userToListenTo: DatabaseReference) {
        this.reportRoot = userToListenTo
    }

    suspend fun getReports(): Flow<DatabaseResult<List<Report?>>> =
        callbackFlow {
            trySend(DatabaseResult.Loading)
            reportRoot.keepSynced(true)


            event = object : ValueEventListener {
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
            reportRoot.addValueEventListener(event as ValueEventListener)
            awaitClose { close() }
        }

    fun insert(newReport: Report) =
        reportRoot.child(UUID.randomUUID().toString()).setValue(newReport)

    fun update(report: Report) {
        val reportID = report.uid.toString()
        var reportEdit = Report(
            firstAider = report.firstAider,
            location = report.location,
            date = report.date,
            time = report.time,
            injuredParty = report.injuredParty,
            injury = report.injury,
            treatment = report.treatment,
            advice = report.advice,
            userID = report.userID
        ).toMap() //gets rid of empty id field in database
        reportRoot.child(reportID).setValue(reportEdit)

    }
//        database.child(userAuthUUID).child(reportID).setValue(editReport)


    fun delete(report: Report) {
        val reportID = report.uid.toString()
        reportRoot.child(reportID).removeValue()
    }


//    fun addNewReport(
//        report: Report, userAuthUUID: String,
//    ) {
//        val key = reportRoot.child(userAuthUUID).push().key
//        if (key == null) {
//            Log.w(TAG, "Couldn't get push key for posts")
//            return
//        }
//
//        val userReport = Report(
//            location = report.location, date = report.date, injury = report.injury
//        ).toMap()
//        val reportValues = report.toMap()
//
//        val childUpdates = hashMapOf<String, Any>(
//            "/$userAuthUUID/$key" to reportValues,
//        )
//        val reportChildUpdates = hashMapOf<String, Any>(
//            "/$userAuthUUID/reports/$key" to userReport,
//
//            )
//
//        reportRoot.updateChildren(childUpdates)
//        userRoot.updateChildren(reportChildUpdates)
//    }
}


package com.example.androidad.core

import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.auth.AuthRepository
import com.example.androidad.data.report.ReportDAO
import com.example.androidad.data.report.ReportRepo
import com.example.androidad.data.report.ReportRepository
import com.example.androidad.data.user.User
import com.example.androidad.data.user.UserDAO
import com.example.androidad.data.user.UserRepo
import com.example.androidad.data.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//private const val CONTACT_ROOT_FOLDER = "contacts"
private const val USER_ROOT_FOLDER = "users"
private const val REPORT_ROOT_FOLDER = "reports"
private const val USERNAME_ROOT_FOLDER = "userNames"
private const val DATABASE_URL =
    "https://androidad-bdfae-default-rtdb.europe-west1.firebasedatabase.app/"

interface AppContainer {
    val userRepository: UserRepo
    val authRepository: AuthRepo
    val reportRepository: ReportRepo
    fun returnContextForDatabaseListener(report: User): DatabaseReference

}

class AppDataContainer : AppContainer {
    override val userRepository: UserRepo
    override val reportRepository: ReportRepo
    override var authRepository: AuthRepo = AuthRepository(FirebaseAuth.getInstance())

    init {
        val userNameRoot =
            FirebaseDatabase.getInstance(DATABASE_URL).getReference(USERNAME_ROOT_FOLDER)

        val userRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(USER_ROOT_FOLDER)
        val userDAO = UserDAO(userRoot, userNameRoot)
        userRepository = UserRepository(userDAO)

        val reportRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(REPORT_ROOT_FOLDER)
        val reportDAO = ReportDAO(reportRoot, userRoot)
        reportRepository = ReportRepository(reportDAO)


    }
}
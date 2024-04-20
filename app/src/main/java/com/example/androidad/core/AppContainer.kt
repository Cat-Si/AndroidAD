package com.example.androidad.core

import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.auth.AuthRepository
import com.example.androidad.data.contact.Contact
import com.example.androidad.data.contact.ContactDAO
import com.example.androidad.data.contact.ContactRepo
import com.example.androidad.data.contact.ContactRepository
import com.example.androidad.data.user.UserDAO
import com.example.androidad.data.user.UserRepo
import com.example.androidad.data.user.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Repo

private const val CONTACT_ROOT_FOLDER = "contacts"
private const val USER_ROOT_FOLDER = "users"
private const val DATABASE_URL ="https://androidad-bdfae-default-rtdb.europe-west1.firebasedatabase.app/"

interface AppContainer {
    val contactRepository: ContactRepo
    val userRepository: UserRepo
    val authRepository: AuthRepo
}

class AppDataContainer : AppContainer {
    override val contactRepository: ContactRepo
    override val userRepository: UserRepo
    override var authRepository: AuthRepo = AuthRepository(FirebaseAuth.getInstance())

    init {
        val contactRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(CONTACT_ROOT_FOLDER)
        val contactDAO = ContactDAO(contactRoot)
        contactRepository = ContactRepository(contactDAO)

        val userRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(USER_ROOT_FOLDER)
        userRepository = UserRepository(UserDAO(userRoot))
    }
}
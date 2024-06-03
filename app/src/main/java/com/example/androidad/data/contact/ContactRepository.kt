package com.example.androidad.data.contact

import android.util.Log
import com.example.androidad.data.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface ContactRepo {
    fun delete(contact: Contact): Task<Void>

    fun add(contact: Contact, contactUUID: String)

    fun edit(contact: Contact, contactUUID: String)

    suspend fun getAll(contactUUID: String): Flow<DatabaseResult<List<Contact?>>>

    fun getUserName(userAuthUUID: String, callback: (String?) -> Unit)
}

class ContactRepository(private val contactDAO: ContactDAO) : ContactRepo {
    override fun delete(contact: Contact) = contactDAO.delete(contact)

    override fun add(contact: Contact, contactUUID: String) {
        contactDAO.insert(contact, contactUUID)
    }

    override fun edit(contact: Contact, contactUUID: String) {
        contactDAO.update(contact, contactUUID)
    }

    override suspend fun getAll(contactUUID: String): Flow<DatabaseResult<List<Contact?>>> {
        return contactDAO.getContacts(contactUUID)
    }

    override fun getUserName(userAuthUUID: String, callback: (String?) -> Unit) {
        contactDAO.getContactByUserId(userAuthUUID) { contact ->
            if (contact != null) {
                Log.d("ContactRepository", "Retrieved username: ${contact.displayName}")
            } else {
                Log.d("ContactRepository", "Contact is null for userId: $userAuthUUID")
            }
            callback(contact?.displayName)
        }
    }
}
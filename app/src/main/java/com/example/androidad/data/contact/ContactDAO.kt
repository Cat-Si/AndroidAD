package com.example.androidad.data.contact

import android.util.Log
import com.example.androidad.data.DatabaseResult
import com.google.android.play.integrity.internal.c
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class ContactDAO(private val database: DatabaseReference) {

    suspend fun getContacts(contactUUID: String): Flow<DatabaseResult<List<Contact?>>> =
        callbackFlow {
            trySend(DatabaseResult.Loading)
            database.child(contactUUID).keepSynced(true)

            val event = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val contacts = ArrayList<Contact>()
                    for (childSnapshot in snapshot.children) {
                        val contact = childSnapshot.getValue(Contact::class.java)
                        contact!!.id = childSnapshot.key.toString()
                        contacts.add(contact)
                    }
                    trySend(DatabaseResult.Success(contacts))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(DatabaseResult.Error(Throwable(error.message)))
                }
            }
            database.child(contactUUID).addValueEventListener(event)
            awaitClose { close() }
        }

    fun insert(newContact: Contact, userAuthUUID: String) =
        database.child(userAuthUUID).setValue(newContact)

    fun update(editContact: Contact, userAuthUUID: String) {
        val contactId = editContact.id.toString() //retrieved for sub folder key
        editContact.id = String() //Clear so not saved inside folder
        database.child(userAuthUUID).setValue(editContact)
    }

    fun delete(contact: Contact) = database.child(contact.id.toString()).removeValue()

    fun getContactByUserId(userAuthUUID: String, callback: (Contact?) -> Unit) {

        database.child(userAuthUUID)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val contact = dataSnapshot.getValue(Contact::class.java)
                    if (contact != null) {
                        Log.d("ContactDAO", "Retrieved contact: ${contact.userName}")
                    } else {
                        Log.d("ContactDAO", "Contact is null for userId: $userAuthUUID")
                    }
                    callback(contact)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("ContactDAO", "Error retrieving contact: ${databaseError.message}")
                    callback(null)
                }
            })
    }


//    val getContact = object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            val contact = dataSnapshot.getValue<Contact>()
//            val username = contact?.userName
//            if (contact != null) {
//                Log.d("ContactDAO", "Retrieved contact: ${contact.userName}")
//            } else {
//                Log.d("ContactDAO", "Contact is null for userId")
//            }
//            return username
//        }
//
//        override fun onCancelled(databaseError: DatabaseError) {
//            Log.e("ContactDAO", "Error retrieving contact: ${databaseError.message}")
//        }
//    }
}



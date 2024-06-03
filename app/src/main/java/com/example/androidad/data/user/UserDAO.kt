package com.example.androidad.data.user

import com.example.androidad.data.DatabaseResult
import com.example.androidad.data.userName.UsernameMapping
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserDAO(
    private val database: DatabaseReference,
    private val userNameRoot: DatabaseReference
) {
    suspend fun getAll(): Flow<DatabaseResult<List<User?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.keepSynced(true)

        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = ArrayList<User>()
                for (childSnapshot in snapshot.children) {
                    val user = childSnapshot.getValue(User::class.java)
                    user!!.uuid = childSnapshot.key.toString()
                    users.add(user)
                }
                trySend(DatabaseResult.Success(users))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(DatabaseResult.Error(Throwable(error.message)))
            }
        }
        database.addValueEventListener(event)
        awaitClose { close() }
    }

    fun insert(newUser: User, userId: String) = database.child(userId).setValue(newUser)

    fun addUser(newUser: User, userUID: String) {

        // Add user to "users" node
        database.child(userUID).setValue(newUser)
            .addOnSuccessListener {
                // Create username mapping


                // Add username mapping to "usernames" node
                userNameRoot.child(newUser.userName.toString())
                    .setValue(userUID)
                    .addOnSuccessListener {
                        // Successfully added user and username mapping
                        println("User and username mapping added successfully")
                    }
                    .addOnFailureListener { exception ->
                        // Handle any errors
                        println("Error adding username mapping: ${exception.message}")
                    }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                println("Error adding user: ${exception.message}")
            }
    }

}

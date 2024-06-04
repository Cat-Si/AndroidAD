package com.example.androidad.data.user

import android.util.Log
import com.example.androidad.data.DatabaseResult
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun getAll(): Flow<DatabaseResult<List<User?>>>
    fun add(entry: User, userId: String)

    fun getDisplayName(userAuthUUID: String, callback: (String?) -> Unit)

    fun getUser(userAuthUUID: String, callback: (User?) -> Unit)

}

class UserRepository(private val dao: UserDAO) : UserRepo {
    override suspend fun getAll(): Flow<DatabaseResult<List<User?>>> {
        return dao.getAll()
    }

    override fun add(entry: User, userId: String) = dao.addUser(entry, userId)

    override fun getDisplayName(userAuthUUID: String, callback: (String?) -> Unit) {
        dao.getUserByUserId(userAuthUUID) { user ->
            if (user != null) {
                Log.d("ContactRepository", "Retrieved username: ${user.displayName}")
            } else {
                Log.d("ContactRepository", "Contact is null for userId: $userAuthUUID")
            }
            callback(user?.displayName)
        }
    }

    override fun getUser(userAuthUUID: String, callback: (User?) -> Unit) {
        dao.getUserByUserId(userAuthUUID) { user ->
            if (user != null) {
                Log.d("UserRepository", "Retrieved user: ${user.displayName}")
            } else {
                Log.d("UserRepository", "User is null for userId: $userAuthUUID")
            }
            callback(user)
        }
    }


}
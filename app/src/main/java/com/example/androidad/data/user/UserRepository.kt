package com.example.androidad.data.user

import com.example.androidad.data.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun getAll(): Flow<DatabaseResult<List<User?>>>
    fun add(entry: User, userId: String)
}

class UserRepository(private val dao: UserDAO) : UserRepo {
    override suspend fun getAll(): Flow<DatabaseResult<List<User?>>> {
        return dao.getAll()
    }

    override fun add(entry: User, userId: String) = dao.addUser(entry, userId)

}
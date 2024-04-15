package com.example.androidad.core

import android.app.Application
import com.example.androidad.data.Contact
import com.example.androidad.data.InMemoryRepository
import java.util.UUID

class ContactApplication : Application()  {
    companion object {
        lateinit var contactInMemoryRepository:
                InMemoryRepository
    }

    override fun onCreate() {
        super.onCreate()
        contactInMemoryRepository =
            InMemoryRepository(ArrayList())

        contactInMemoryRepository.addContact(Contact(UUID.randomUUID(),"firstname1","surname1","tel1"))
        contactInMemoryRepository.addContact(Contact(UUID.randomUUID(),"firstname2","surname2","tel2"))
        contactInMemoryRepository.addContact(Contact(UUID.randomUUID(),"firstname3","surname3","tel3"))
    }
}

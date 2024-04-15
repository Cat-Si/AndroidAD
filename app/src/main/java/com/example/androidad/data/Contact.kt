package com.example.androidad.data

import java.util.*

data class Contact(
    var id: UUID,
    var firstName: String,
    var surname: String,
    var telNo: String
)  {
    override fun toString(): String = "$firstName $surname $telNo"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val contact = other as Contact
        return (contact.id == id)
    }
}


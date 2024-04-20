package com.example.androidad.data.contact

import java.util.*

data class Contact(
    var firstName: String? =null,
    var surname: String? =null,
    var telNo: String? =null
) {
    var id:String? =null //uuid
    override fun toString(): String = "$firstName $surname $telNo"

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || javaClass != other.javaClass) return false
//        val contact = other as Contact
//        return (contact.id == id)
//    }
}


package com.example.androidad.data.contact

import com.example.androidad.data.report.Report

data class Contact(
    var firstName: String? = null,
    var surname: String? = null,
    var userName: String? = null,
    var report: List<Report>? = null
) {
    var id: String? = null //uuid
    override fun toString(): String = "$firstName $surname $userName"

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || javaClass != other.javaClass) return false
//        val contact = other as Contact
//        return (contact.id == id)
//    }
}


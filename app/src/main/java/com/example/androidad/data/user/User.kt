package com.example.androidad.data.user

data class User(
    var email: String? =null
) {
    var uuid:String? =null
    override fun toString(): String = "$email"
}
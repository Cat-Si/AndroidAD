package com.example.androidad.data.user

data class User(
    var email: String? = null,
    var displayName: String? = null,
    var userName: String? = null,
    /*    var firstName: String? =null,
        var lastName: String? =null*/
) {
    var uuid: String? = null
    override fun toString(): String = "$email"
}
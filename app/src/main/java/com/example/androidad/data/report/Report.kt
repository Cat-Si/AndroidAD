package com.example.androidad.data.report

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report(
    var firstAider: String? = null,
    var location: String? = null,
    var date: String? = null,
    var time: String? = null,
    var injuredParty: String? = null,
    var injury: String? = null,
    var treatment: String? = null,
    var advice: String? = null,
) {
    var uid: String? = null //uuid

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "firstAider" to firstAider,
            "location" to location,
            "date" to date,
            "time" to time,
            "injuredParty" to injuredParty,
            "injury" to injury,
            "treatment" to treatment,
            "advice" to advice,

            )
    }

    override fun toString(): String =
        "$firstAider $location $date $time $injuredParty $injury $treatment $advice"
}
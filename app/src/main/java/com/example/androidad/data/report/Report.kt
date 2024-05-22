package com.example.androidad.data.report

data class Report(
    var firstAider: String? =null,
    var location: String? =null,
    var date: String? =null,
    var time: String? =null,
    var injuredParty: String? =null,
    var injury: String? =null,
    var treatment: String? =null,
    var advice: String? =null,
) {
    var id:String? =null //uuid
    override fun toString(): String = "$firstAider $location $date $time $injuredParty $injury $treatment $advice"
}
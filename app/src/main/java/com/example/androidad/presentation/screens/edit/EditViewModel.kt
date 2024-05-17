package com.example.androidad.presentation.screens.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.contact.Contact
import com.example.androidad.data.contact.ContactRepo
import com.example.androidad.data.report.Report
import com.example.androidad.data.report.ReportRepo

class EditViewModel (private val authRepo: AuthRepo, private val repo: ReportRepo) : ViewModel() {
    private var selectedReport : Report? = null

    var id by mutableStateOf(String())
    var location by mutableStateOf(String())
    var date by mutableStateOf(String())
    var time by mutableStateOf(String())
    var injuredParty by mutableStateOf(String())
    var injury by mutableStateOf(String())
    var treatment by mutableStateOf(String())
    var advice by mutableStateOf(String())

    fun setSelectedReport(report: Report){
        id = report.id.toString()
       location = report.location.toString()
        date = report.date.toString()
        time = report.time.toString()
        injuredParty = report.injuredParty.toString()
        injury = report.injury.toString()
        treatment = report.treatment.toString()
        advice = report.advice.toString()
        selectedReport = report
    }

    fun locationIsValid():Boolean{
        return location.isNotBlank()
    }
    fun dateIsValid():Boolean{
        return date.isNotBlank()
    }

    fun timeIsValid():Boolean{
        return time.isNotBlank()
    }

    fun injuredPartyIsValid():Boolean{
        return injuredParty.isNotBlank()
    }

    fun injuryIsValid():Boolean{
        return injury.isNotBlank()
    }

    fun treatmentIsValid():Boolean{
        return treatment.isNotBlank()
    }
    fun adviceIsValid():Boolean{
        return advice.isNotBlank()
    }

    fun updateReport(){
        if (locationIsValid()
            && dateIsValid()
            && timeIsValid()
            && injuredPartyIsValid()
            && injuryIsValid()
            && treatmentIsValid()
            && adviceIsValid())  {
            selectedReport!!.location = location
            selectedReport!!.date = date
            selectedReport!!.time = time
            selectedReport!!.injuredParty = injuredParty
            selectedReport!!.injury = injury
            selectedReport!!.treatment = treatment
            selectedReport!!.advice = advice

            repo.edit(selectedReport!!, authRepo.currentUser!!.uid)
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory= viewModelFactory() {
            initializer {
                EditViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.reportRepository
                )
            }
        }
    }
}
package com.example.androidad.presentation.screens.add

import android.util.Log
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

class AddViewModel(
    private val authRepo: AuthRepo,
    private val reportRepo: ReportRepo,
    private val contactRepo: ContactRepo,
) : ViewModel() {
    var firstAider by mutableStateOf(String())
    var location by mutableStateOf(String())
    var date by mutableStateOf(String())
    var time by mutableStateOf(String())
    var injuredParty by mutableStateOf(String())
    var injury by mutableStateOf(String())
    var treatment by mutableStateOf(String())
    var advice by mutableStateOf(String())


    var submissionFailed by mutableStateOf(false)


    init {
        loadDisplayName()
    }


    private fun loadDisplayName() {
        val userId = authRepo.currentUser?.uid
        if (userId != null) {
            contactRepo.getDisplayName(userId) { userName ->
                Log.d("AddViewModel", "Setting firstAider to: $userName")
                firstAider = userName ?: ""
            }
        } else {
            Log.d("AddViewModel", "User ID is null")
        }
    }


    fun firstAiderIsValid(): Boolean {
        return firstAider.isNotBlank()
    }

    fun locationIsValid(): Boolean {
        return location.isNotBlank()
    }

    fun dateIsValid(): Boolean {
        return date.isNotBlank()
    }

    fun timeIsValid(): Boolean {
        return time.isNotBlank()
    }

    fun injuredPartyIsValid(): Boolean {
        return injuredParty.isNotBlank()
    }

    fun injuryIsValid(): Boolean {
        return injury.isNotBlank()
    }

    fun treatmentIsValid(): Boolean {
        return treatment.isNotBlank()
    }

    fun adviceIsValid(): Boolean {
        return advice.isNotBlank()
    }


    fun addReport() {
        if (
            firstAiderIsValid()
            && locationIsValid()
            && timeIsValid()
            && injuredPartyIsValid()
            && injuryIsValid()
            && treatmentIsValid()
            && adviceIsValid()
        ) {

            var newReport = Report(
                firstAider,
                location,
                date,
                time,
                injuredParty,
                injury,
                treatment,
                advice,
            )
            reportRepo.add(newReport, authRepo.currentUser!!.uid)
            submissionFailed = false
            clear()
        } else {
            submissionFailed = true
        }
    }

    private fun clear() {
        firstAider = String()
        location = String()
        date = String()
        time = String()
        injuredParty = String()
        injury = String()
        treatment = String()
        advice = String()
        submissionFailed = false
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    reportRepo = ContactApplication.container.reportRepository,
                    contactRepo = ContactApplication.container.contactRepository

                )
            }
        }

    }
}


/*  @OptIn(ExperimentalMaterial3Api::class)
  fun dateAsString(dateState: DatePickerState): String {
      val millisToLocalDate = dateState.selectedDateMillis?.let {
          DateUtil().convertMillisToLocalDate(it)
      }
      val dateToString = millisToLocalDate?.let {
          DateUtil().dateToString(millisToLocalDate)
      } ?: "Choose Date"

      return dateToString
  }*/
/*    fun checkFormIsValid():Boolean{
      if(location.isBlank()) return false
        if (date.isBlank()) return false
        if (time.isBlank()) return false
        if (injuredParty.isBlank()) return false
        if (injury.isBlank()) return false
        if (treatment.isBlank()) return false
        if (advice.isBlank()) return false
        return true
    }*/
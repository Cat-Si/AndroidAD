package com.example.androidad.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.DatabaseResult
import com.example.androidad.data.DatabaseState
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.report.Report
import com.example.androidad.data.report.ReportRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val authRepo: AuthRepo, private val repo: ReportRepo) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<Report>())
    val userState: StateFlow<DatabaseState<Report>> =
        _userState.asStateFlow()//Monitored by component for recomposition on change

    var selectedReport: Report? = null
    var selectedIndex by mutableIntStateOf(-1)
    fun selectReport(index: Int, report: Report?) {
        selectedIndex = index
        selectedReport = report
    }


    init {
        getListOfReports(authRepo.currentUser!!.uid)
    }

    fun reportHasBeenSelected(): Boolean = selectedReport != null

    private fun getListOfReports(userId: String) = viewModelScope.launch {
        repo.getAll(userId).collect { result ->
            when (result) {
                is DatabaseResult.Success -> {
                    _userState.update { it.copy(data = result.data) }
                }

                is DatabaseResult.Error -> {
                    _userState.update {
                        it.copy(errorMessage = result.exception.message!!)
                    }
                }

                is DatabaseResult.Loading -> {
                    _userState.update { it.copy(isLoading = true) }
                }
            }
        }
    }


    fun deleteReport() {
//        Log.v("OK","calling delete")
        if (reportHasBeenSelected()) {
//            Log.v("OK",selectedReport.toString())
            repo.delete(selectedReport!!, authRepo.currentUser!!.uid)
            selectedReport = null
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.reportRepository
                )
            }
        }

    }
}

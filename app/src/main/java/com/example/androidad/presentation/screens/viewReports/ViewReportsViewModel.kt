package com.example.androidad.presentation.screens.viewReports

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.androidad.data.user.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewReportsViewModel(private val authRepo: AuthRepo, private val repo: ReportRepo) :
    ViewModel() {
    var selectedReport: Report? = null
    private val _reportState = MutableStateFlow(DatabaseState<Report>())
    val reportState: StateFlow<DatabaseState<Report>> =
        _reportState.asStateFlow()//Monitored by component for recomposition on change

    fun setSelectedUser(user: User) {
        repo.updateUserListener(ContactApplication.container.returnContextForDatabaseListener(user))
        getListOfReports()
    }


    var selectedIndex by mutableIntStateOf(-1)
    fun selectReport(index: Int, report: Report?) {
        selectedIndex = index
        selectedReport = report
    }


    init {
        getListOfReports()
    }

    fun reportHasBeenSelected(): Boolean = selectedReport != null

    private fun getListOfReports() = viewModelScope.launch {
        repo.getAll().collect { result ->
            when (result) {
                is DatabaseResult.Success -> {
                    _reportState.update { it.copy(data = result.data) }
                }

                is DatabaseResult.Error -> {
                    _reportState.update {
                        it.copy(errorMessage = result.exception.message!!)
                    }
                }

                is DatabaseResult.Loading -> {
                    _reportState.update { it.copy(isLoading = true) }
                }
            }
        }
    }


    //delete is managed in edit


    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ViewReportsViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.reportRepository
                )
            }
        }

    }
}

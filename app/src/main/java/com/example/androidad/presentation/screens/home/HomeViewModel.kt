package com.example.androidad.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.DatabaseResult
import com.example.androidad.data.DatabaseState
import com.example.androidad.data.user.User
import com.example.androidad.data.user.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: UserRepo) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<User>())
    val userState: StateFlow<DatabaseState<User>> =
        _userState.asStateFlow()//Monitored by component for recomposition on change

    var selectedUser: User? = null

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        repo.getAll().collect { result ->
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

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    repo = ContactApplication.container.userRepository
                )
            }
        }

    }
}

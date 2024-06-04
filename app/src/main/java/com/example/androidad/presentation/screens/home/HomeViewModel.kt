package com.example.navigationwithviewmodel1.presentation.screens.home

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.navigationwithviewmodel1.core.ContactApplication
import com.example.navigationwithviewmodel1.data.*
import com.example.navigationwithviewmodel1.data.user.User
import com.example.navigationwithviewmodel1.data.user.UserRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: UserRepo) : ViewModel() {
    private val _userState = MutableStateFlow(DatabaseState<User>())
    val userState: StateFlow<DatabaseState<User>> = _userState.asStateFlow()//Monitored by component for recomposition on change

    var selectedUser: User?= null

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        repo.getAll().collect { result ->
            when(result) {
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

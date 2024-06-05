package com.example.androidad.presentation.screens.login


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.Response
import com.example.androidad.data.auth.AuthRepo
import com.example.androidad.data.report.ReportRepo
import com.example.androidad.data.user.User
import com.example.androidad.data.user.UserRepo
import com.example.androidad.presentation.navigation.NavScreen
import com.google.android.play.integrity.internal.f
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repo: AuthRepo,
    val userRepo: UserRepo,
    private val reportRepo: ReportRepo
) : ViewModel() {
    var email by mutableStateOf(String())
    var password by mutableStateOf(String())

    var submissionFailed by mutableStateOf(false)


    var signInResponse by mutableStateOf<Response<Boolean>>(Response.Startup)
        private set

    private var _message = MutableLiveData(String())
    var message: LiveData<String> = _message

    fun emailIsValid(): Boolean {
        return email.isNotBlank()
    }

    fun passwordIsValid(): Boolean {
        return password.isNotBlank()
    }

    fun getCurrentUser(): User {
        val user = User(repo.currentUser!!.email)
        user.uuid = repo.currentUser!!.uid
        return user
    }

    var isAdmin = getCurrentUser().admin == true

    fun forgotPassword() {
        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _message.value = "Password reset email has been sent successfully"
                } else {
                    _message.value = "Unable to send password reset email"
                }
            }
    }


    fun signInWithEmailAndPassword() =
        viewModelScope.launch {
            signInResponse = Response.Loading
            signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)

            Log.v("ISADMIN??", isAdmin.toString())
        }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    repo = ContactApplication.container.authRepository,
                    userRepo = ContactApplication.container.userRepository,
                    reportRepo = ContactApplication.container.reportRepository
                )
            }
        }
    }
}

package com.example.androidad.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidad.R
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.report.Report
import com.example.androidad.data.user.User
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.screens.add.AddScreen
import com.example.androidad.presentation.screens.edit.EditScreen
import com.example.androidad.presentation.screens.home.HomeScreen
import com.example.androidad.presentation.screens.login.LoginScreen
import com.example.androidad.presentation.screens.signup.SignUpScreen
import com.example.androidad.presentation.screens.viewReports.ViewReportsScreen
import kotlin.system.exitProcess

sealed class NavScreen(var icon: Int, var route: String, var label: String) {
    data object Home : NavScreen(R.drawable.home, "Home", "Home")
    data object Add : NavScreen(R.drawable.add, "Add", "Add")
    data object Edit : NavScreen(R.drawable.add, "Edit", "Edit")//drawable is not relevant
    data object Exit : NavScreen(R.drawable.logout, "Logout", "Logout")
    data object Login : NavScreen(R.drawable.home, "Login", "Login")
    data object SignUp : NavScreen(R.drawable.home, "SignUp", "Sign Up")
    data object ViewReports : NavScreen(R.drawable.home, "ViewReports", "Reports")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
//    datePickerState: DatePickerState = rememberDatePickerState(),

) {
    var selectedReport: Report? = null
    var selectedUser: User? = null
    var loggedInUser: User? = null


    NavHost(navController, startDestination = NavScreen.Login.route) {

        composable(NavScreen.Login.route) {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(NavScreen.SignUp.route)
                },
                navigateToHome = {
                    navController.navigate(NavScreen.Home.route)
                    loggedInUser = it
                },
                navigateToViewReports = {
                    navController.navigate(NavScreen.ViewReports.route)
                    selectedUser = it
                    loggedInUser = it
                }
            )
        }
        composable(NavScreen.SignUp.route) {
            SignUpScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(NavScreen.Home.route) {
            HomeScreen(
                navController = navController,
                onIndexChange = {
                    selectedUser = it
                },
                onClickToViewReports = {
                    if (selectedUser != null) navController.navigate(NavScreen.ViewReports.route)
                },
            )
        }
        composable(NavScreen.Add.route) {
            AddScreen(
                navController = navController,
                onClickToViewReport = { navController.navigate(NavScreen.ViewReports.route) },
            )
        }
        composable(NavScreen.Edit.route) {
            EditScreen(
                navController = navController,
                selectedReport = selectedReport!!,
                onClickToViewReport = {
                    navController.navigate(NavScreen.ViewReports.route)
                },
            )
        }
        composable(NavScreen.ViewReports.route) {
            ViewReportsScreen(
                selectedUser = selectedUser!!,
                navController = navController,
                onIndexChange = {
                    selectedReport = it
                },
                onClickToEdit = {
                    if (selectedReport != null) navController.navigate("edit")
                },
                isAdmin = loggedInUser!!.admin == true,
                onClickToHome = {
                    navController.navigate(NavScreen.Home.route)
                }

            )
        }
        composable(NavScreen.Exit.route) {
            ContactApplication.container.authRepository.signOut()
            exitProcess(0)
        }
    }


}



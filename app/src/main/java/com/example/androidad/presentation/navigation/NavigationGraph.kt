package com.example.androidad.presentation.navigation

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidad.R
import com.example.androidad.core.ContactApplication
import com.example.androidad.data.report.Report
import com.example.androidad.presentation.screens.add.AddScreen
import com.example.androidad.presentation.screens.edit.EditScreen
import com.example.androidad.presentation.screens.home.HomeScreen
import com.example.androidad.presentation.screens.login.LoginScreen
import com.example.androidad.presentation.screens.signup.SignUpScreen
import com.example.androidad.presentation.utils.DatePickerFragment
import kotlin.system.exitProcess

sealed class NavScreen(var icon:Int, var route:String){
    data object Home: NavScreen(R.drawable.home, "Home")
    data object Add: NavScreen(R.drawable.add, "Add")
    data object Edit: NavScreen(R.drawable.add, "Edit")//drawable is not relevant
    data object Exit: NavScreen(R.drawable.logout, "Logout")
    data object Login: NavScreen(R.drawable.home, "Login")
    data object SignUp: NavScreen(R.drawable.home, "SignUp")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    datePickerState: DatePickerState = rememberDatePickerState(),

    ) {
    var selectedReport: Report? =null

    NavHost(navController, startDestination = NavScreen.Login.route) {

        composable(NavScreen.Login.route) {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(NavScreen.SignUp.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(NavScreen.Home.route)
                }
            )
        }
        composable(NavScreen.SignUp.route) {
            SignUpScreen(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(NavScreen.Home.route) {
            HomeScreen(
                navController = navController,
                onIndexChange = {
                    selectedReport = it
                },
                onClickToEdit = {
                    if(selectedReport!=null) navController.navigate("edit")
                }

                )
        }
        composable(NavScreen.Add.route) {
            AddScreen(
                navController = navController,
                onClickToHome ={ navController.popBackStack()},
                datePickerState = datePickerState
            )
        }
        composable(NavScreen.Edit.route) {
            EditScreen(navController = navController,
                selectedReport=selectedReport!!,
                onClickToHome = {
                    if(selectedReport!=null) {
                        navController.navigate("home")
                    }
                })
        }
        composable(NavScreen.Exit.route) {
            ContactApplication.container.authRepository.signOut()
            exitProcess(0)
        }
    }
}



//Application code below
//@Composable
//fun NavigationGraph(
//    navController: NavHostController,
//    context: Context,
//    simulateLogin: () -> Unit,
//    modifier: Modifier
//) {
//    NavHost(navController,
//        startDestination = NavScreen.Login.route) {
//        composable(NavScreen.Login.route) {
//            LoginScreen(stringResource(R.string.login_button), simulateLogin, modifier)
//        }
//        composable(NavScreen.Home.route) {
//            HomeScreen(stringResource(R.string.home_button), HomeViewModel, context, modifier)
//        }
//        composable(NavScreen.Search.route) {
//            SearchScreen(stringResource(R.string.search_button), modifier)
//        }
//        composable(NavScreen.Exit.route) {
//            exitProcess(0)
//        }
//    }
//}

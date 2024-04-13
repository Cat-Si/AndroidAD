package com.example.androidad.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidad.R
import com.example.androidad.presentation.screens.HomeScreen
import com.example.androidad.presentation.screens.LoginScreen
import com.example.androidad.presentation.screens.SearchScreen
import kotlin.system.exitProcess

open class NavScreen(var icon:Int, var route:String){
    object Login : NavScreen(R.drawable.home, "Login")
    object Home : NavScreen(R.drawable.home, "Home")
    object Search: NavScreen(R.drawable.computer_search, "Search")
    object Exit: NavScreen(R.drawable.logout, "Logout")
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    context: Context,
    simulateLogin: () -> Unit,
    modifier: Modifier
) {
    NavHost(navController,
        startDestination = NavScreen.Login.route) {
        composable(NavScreen.Login.route) {
            LoginScreen(stringResource(R.string.login_button), simulateLogin, modifier)
        }
        composable(NavScreen.Home.route) {
            HomeScreen(stringResource(R.string.home_button), context, modifier)
        }
        composable(NavScreen.Search.route) {
            SearchScreen(stringResource(R.string.search_button), modifier)
        }
        composable(NavScreen.Exit.route) {
            exitProcess(0)
        }
    }
}

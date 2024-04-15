package com.example.androidad.presentation.navigation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidad.R
import com.example.androidad.presentation.screens.view_delete.HomeScreen
import com.example.androidad.presentation.screens.login.LoginScreen
import com.example.androidad.presentation.screens.search.SearchScreen
import com.example.androidad.presentation.screens.view_delete.HomeViewModel
import com.example.bottomnav1.presentation.screens.add.AddScreen
import com.example.bottomnav1.presentation.screens.edit.EditScreen
import kotlin.system.exitProcess

open class NavScreen(var icon:Int, var route:String){
    object Login : NavScreen(R.drawable.home, "Login")
    object Home : NavScreen(R.drawable.home, "Home")
    object Search: NavScreen(R.drawable.computer_search, "Search")
    object Exit: NavScreen(R.drawable.logout, "Logout")

    //lecture example:
    data object Add: NavScreen(R.drawable.add, "Add")
    data object Edit: NavScreen(R.drawable.add, "Edit")//drawable is not relevant
}

//Lecture example:
@Composable
fun NavigationGraph(navController: NavHostController,
                    context:Context,
                    modifier: Modifier = Modifier.testTag("TestNavGraph")) {
    //Avoid Bundle/Parcelable here - just store index of selected Contact
    var selectedContactIndex by remember{ mutableIntStateOf(-1) }

    NavHost(navController,
        startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(
                context = context,
                selectedIndex = selectedContactIndex,
                onIndexChange = {
                    Log.v("OK","index change event called")
                    selectedContactIndex = it
                },
                onClickToEdit = {
                    if (selectedContactIndex != -1) navController.navigate("edit")
                    else{
                        Toast.makeText(context,
                            context.getString(R.string.no_selection),
                            Toast.LENGTH_LONG).show();
                    }
                }
            );
        }
        composable(NavScreen.Add.route) {
            AddScreen(
                onClickToHome ={navController.navigate("home")})
        }
        composable(NavScreen.Edit.route) {
            EditScreen(selectedContactIndex=selectedContactIndex,
                onClickToHome = {if(selectedContactIndex!=-1)
                    navController.navigate("home")
                })
        }
        composable(NavScreen.Exit.route){
            exitProcess(0);
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

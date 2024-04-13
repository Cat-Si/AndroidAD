package com.example.androidad.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.navigation.NavigationGraph



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OverallDisplay(modifier: Modifier = Modifier,
                   navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current.applicationContext  //context only needed for pop up as it refers to itself (pg 9 Bottom Nav pdf)
    var loginAuthenticated by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController, loginAuthenticated)
        }
    ) {
        NavigationGraph(navController = navController,
            context, //context only needed for the pop up box
            simulateLogin = {loginAuthenticated=true
                navController.navigate("home")},
            modifier)
    }
}
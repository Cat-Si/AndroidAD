package com.example.androidad.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidad.R
import com.example.androidad.navigation.NavScreen

@Composable
fun BottomNavBar(navController: NavController, enabled: Boolean) {
    val itemsList = createListOfItems(enabled)

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        itemsList.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,

                icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
                label = { Text(text = item.route,
                    fontSize = 9.sp) },
                alwaysShowLabel = true,

                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun createListOfItems(enabled: Boolean): List<NavScreen> {
    return if(enabled){
        listOf(
            NavScreen.Home,
            NavScreen.Search,
            NavScreen.Exit
        )
    }
    else{
        listOf(
            NavScreen.Exit
        )
    }
}
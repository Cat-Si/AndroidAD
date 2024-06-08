package com.example.androidad.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.playservices.HiddenActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidad.R
import com.example.androidad.presentation.navigation.NavScreen


@Composable
fun BottomNavBar(navController: NavController, isAdmin: Boolean) {
    BottomNavigation(
        modifier = Modifier.semantics { contentDescription = "bottom nav" },
        backgroundColor = colorResource(id = R.color.white),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        createListOfItems(isAdmin).forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = "nav" + item.label,
                        Modifier.size(15.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
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
private fun createListOfItems(isAdmin: Boolean): List<NavScreen> {
    return if (isAdmin) {
        listOf(
            NavScreen.ViewReports,
            NavScreen.Exit,
        )
    } else {
        listOf(
            NavScreen.ViewReports,
            NavScreen.Add,
            NavScreen.Exit,
        )
    }
//    return listOf(
//        if (isAdmin) {
//            NavScreen.ViewReports
//            NavScreen.Exit
//        } else {
//            NavScreen.ViewReports
//            NavScreen.Add
//            NavScreen.Exit
//        }
//    )
}

/*@Composable
private fun createListOfItems(enabled: Boolean): List<NavScreen> {
    return if(enabled){
        listOf(
            NavScreen.Home,
            NavScreen.Search,
            NavScreen.Exit
            NavScreen.LogOut

        )
    }
    else{
        listOf(
            NavScreen.Exit
        )
    }
}*/


//@Composable
//fun BottomNavBar(navController: NavController) {
//    BottomNavigation(modifier = Modifier.testTag("bottom_nav"),
//        backgroundColor = colorResource(id = R.color.white),
//        contentColor = Color.Black
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        createListOfItems().forEach { item ->
//            BottomNavigationItem(
//                icon = { Icon(painterResource(id = item.icon), contentDescription = item.route) },
//                label = { Text(text = item.route,
//                    fontSize = 9.sp) },
//                selectedContentColor = Color.Black,
//                unselectedContentColor = Color.Black.copy(0.4f),
//                alwaysShowLabel = true,
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let { screenRoute ->
//                            popUpTo(screenRoute) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//private fun createListOfItems(): List<NavScreen> {
//    return listOf(
//        NavScreen.Home,
//        NavScreen.Add,
//        NavScreen.Exit
//    )
//}


/*   APPLICATION CODE
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
}*/

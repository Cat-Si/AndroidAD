package com.example.androidad.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidad.R
import com.example.androidad.data.user.User
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.screens.home.components.LazyColumnWithSelection
import com.example.androidad.presentation.utils.Util.Companion.showMessage

@SuppressLint(
    "CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    onClickToViewReports: () -> Unit,
    onIndexChange: (User?) -> Unit,
    navController: NavHostController,
    isAdmin: Boolean

) {

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController, isAdmin = isAdmin)
        }
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it) // Padding from the Scaffold
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp, bottom = 10.dp),
                    text = stringResource(R.string.home_screen_title),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 5.dp, bottom = 10.dp, start = 10.dp),
                    text = stringResource(R.string.selectUser),
                    textAlign = TextAlign.Left,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                val userState by vm.userState.collectAsState()

                if (userState.data.isNotEmpty()) //Some data to display
                    LazyColumnWithSelection(vm, onIndexChange)

                if (vm.userState.value.errorMessage.isNotBlank()) { //Problem retrieving data
                    showMessage(context, vm.userState.value.errorMessage)
                }
            }
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            )
            {
                CustomButton(
                    stringResource(R.string.viewReports),
                    clickButton = { onClickToViewReports() },
                    modifier = Modifier
                        .padding(bottom = 30.dp)


                )
            }
        }
    }
}





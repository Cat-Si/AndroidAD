package com.example.androidad.presentation.screens.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.androidad.data.report.Report
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.screens.home.components.ItemView
import com.example.androidad.presentation.utils.Util.Companion.showMessage

@SuppressLint(
    "StateFlowValueCalledInComposition",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
    modifier: Modifier = Modifier,
    onIndexChange: (Report?) -> Unit,
    onClickToEdit: () -> Unit,
    navController: NavHostController
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp, bottom = 10.dp),
                text = "Submitted Reports",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            val userState by vm.userState.collectAsState()

            LazyColumn(
                modifier = Modifier.weight(1f) // Take up available space
            ) {
                if (userState.data.isNotEmpty()) {
                    itemsIndexed(userState.data) { index, item ->
                        if (item != null) {
                            ItemView(
                                index = index,
                                report = item,
                                selected = vm.selectedIndex == index,
                                onClick = { selectedIndex ->
                                    vm.selectReport(selectedIndex, item)
                                    onIndexChange(item)
                                }
                            )
                        }
                    }
                }
            }

            if (vm.userState.value.errorMessage.isNotBlank()) {
                LaunchedEffect(key1 = vm.userState.value.errorMessage) {
                    showMessage(context, vm.userState.value.errorMessage)
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                CustomButton(
                    stringResource(R.string.edit),
                    clickButton = {

                        if (!vm.reportHasBeenSelected()) {
                            Toast.makeText(
                                context,
                                "No reports available to edit",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        } else {
                            onClickToEdit()
                        }
                    },
//                    modifier.padding(bottom = 5.dp)
                )
            }


        }
    }
}


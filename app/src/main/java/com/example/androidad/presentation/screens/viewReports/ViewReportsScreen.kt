package com.example.androidad.presentation.screens.viewReports

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
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
import androidx.wear.compose.material.Button
import com.example.androidad.R
import com.example.androidad.data.report.Report
import com.example.androidad.data.user.User
import com.example.androidad.presentation.components.BottomNavBar
import com.example.androidad.presentation.components.CustomButton
import com.example.androidad.presentation.screens.viewReports.components.ReportItemView
import com.example.androidad.presentation.utils.Util.Companion.showMessage

@SuppressLint(
    "StateFlowValueCalledInComposition",
    "UnusedMaterial3ScaffoldPaddingParameter"
)
@Composable
fun ViewReportsScreen(
    vm: ViewReportsViewModel = viewModel(factory = ViewReportsViewModel.Factory),
    modifier: Modifier = Modifier,
    selectedUser: User,
    onIndexChange: (Report?) -> Unit,
    onClickToEdit: () -> Unit,
    navController: NavHostController,
    isAdmin: Boolean,
    onClickToHome: () -> Unit,
    onClickToAdd: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {//Called on launch
        vm.setSelectedUser(selectedUser)
    }


    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController, isAdmin = isAdmin)
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
                text = stringResource(R.string.view_reports_screen_title),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp, bottom = 10.dp),
                text = "${selectedUser.displayName} (${selectedUser.userName})",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
//            Log.v("SELECTEDUSER", selectedUser.toString())

            val userState by vm.reportState.collectAsState()

            LazyColumn(
                modifier = Modifier.weight(1f) // Take up available space
            ) {
                if (userState.data.isNotEmpty()) {
                    itemsIndexed(userState.data) { index, item ->
                        if (item != null) {
                            ReportItemView(
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

            if (vm.reportState.value.errorMessage.isNotBlank()) {
                LaunchedEffect(key1 = vm.reportState.value.errorMessage) {
                    showMessage(context, vm.reportState.value.errorMessage)
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
                if (isAdmin == selectedUser.admin) {
                    CustomButton(
                        stringResource(R.string.add),
                        clickButton = {
                            onClickToAdd()
                        })
                }
                if (isAdmin) {
                    CustomButton(
                        stringResource(R.string.back),
                        clickButton = {
                            onClickToHome()
                        })
                }


            }

        }
    }
}


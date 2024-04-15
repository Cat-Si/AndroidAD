package com.example.androidad.presentation.screens.view_delete

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidad.R
import com.example.androidad.presentation.components.FloatingButton
import com.example.navigation1.presentation.components.CustomButton

@Composable
fun HomeScreen(vm: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
               context: Context,
               selectedIndex: Int,
               onIndexChange: (Int) -> Unit,//function to change the index
               onClickToEdit: () -> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        androidx.compose.material.Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.home),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        LazyColumnWithSelection(vm,  onIndexChange)
        CustomButton(stringResource(R.string.edit), onClickToEdit)
        CustomButton(stringResource(R.string.delete)) {  //use this to delete the favourite from the favourites table.
            if (selectedIndex!=-1 && vm.items.size>0) {
                vm.deleteContact()
                onIndexChange(-1)
            }
            else{
                Toast.makeText(context,
                    context.getString(R.string.no_selection),
                    Toast.LENGTH_LONG).show();
            }
        }
    }
}

@Composable
fun LazyColumnWithSelection(vm: HomeViewModel,
                            onIndexChange: (Int) -> Unit){
    LazyColumn{
        itemsIndexed(vm.items) { index, item ->
            ItemView(
                index = index,
                item = item.toString(),
                selected = vm.selectedContactIndex == index,
                onClick = {
                    onIndexChange(index)
                    vm.selectedContactIndex = index
                }
            )
        }
    }
}

@Composable
fun ItemView(index: Int,
             item: String,
             selected: Boolean,
             onClick: (Int) -> Unit){
    androidx.compose.material.Text(
        text = item,
        modifier = Modifier
            .clickable {
                onClick.invoke(index)
            }
            .background(if (selected) MaterialTheme.colors.secondary else Color.Transparent)
            .fillMaxWidth()
            .padding(10.dp)
    )
}
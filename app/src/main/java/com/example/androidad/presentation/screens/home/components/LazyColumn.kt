package com.example.androidad.presentation.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androidad.data.user.User
import com.example.androidad.presentation.screens.home.HomeViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LazyColumnWithSelection(
    vm: HomeViewModel,
    onIndexChange: (User) -> Unit
) {
    var selectedIndexToHighlight by remember { mutableIntStateOf(-1) }

    LazyColumn {
        itemsIndexed(vm.userState.value.data) { index, item ->
            ItemView(
                index = index,
                item = item.toString(),
                selected = selectedIndexToHighlight == index,
                onClick = { index: Int ->
                    selectedIndexToHighlight =
                        index //local state for highlighting selected item
                    onIndexChange(item!!)             //for edit
                    vm.selectedUser = item       //for delete
                }
            )
        }
    }
}

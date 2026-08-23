package com.filippochinni.inventoryapp.ui.screen.inventoryGroup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator


@Composable
fun InventoryEditScreen(
	inventoryId: Int,
) {
	Column {
		Text(text = "Inventory Edit Screen")
		Text(text = "Inventory ID: $inventoryId")
		CustomLoadingIndicator()
	}
}

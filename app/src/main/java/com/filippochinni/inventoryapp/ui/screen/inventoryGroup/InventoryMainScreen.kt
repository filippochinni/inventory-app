package com.filippochinni.inventoryapp.ui.screen.inventoryGroup

import androidx.compose.runtime.Composable
import com.filippochinni.inventoryapp.model.Inventory


@Composable
fun InventoryMainScreen(
	onFABClick: () -> Unit,
	onElementClick: (Inventory) -> Unit,
	onEditClick: (Int) -> Unit
) {
	InventoryListScreen(
		onFABClick = onFABClick,
		onElementClick = onElementClick,
		onEditClick = onEditClick
	)
}

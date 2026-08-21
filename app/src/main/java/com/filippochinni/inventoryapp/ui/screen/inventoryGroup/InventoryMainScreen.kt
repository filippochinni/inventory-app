package com.filippochinni.inventoryapp.ui.screen.inventoryGroup

import androidx.compose.runtime.Composable


@Composable
fun InventoryMainScreen(
	onFABClick: () -> Unit
) {
	InventoryListScreen(
		onFABClick = onFABClick
	)
}

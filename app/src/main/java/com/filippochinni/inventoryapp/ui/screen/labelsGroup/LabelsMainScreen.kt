package com.filippochinni.inventoryapp.ui.screen.labelsGroup

import androidx.compose.runtime.Composable
import com.filippochinni.inventoryapp.model.Label


@Composable
fun LabelsMainScreen(
	onFABClick: () -> Unit,
	onElementClick: (Label) -> Unit,
	onEditClick: (Int) -> Unit
	) {
		LabelsListScreen(
			onFABClick = onFABClick,
			onElementClick = onElementClick,
			onEditClick = onEditClick
		)
}

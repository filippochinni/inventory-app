package com.filippochinni.inventoryapp.ui.screen.labelsGroup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator


@Composable
fun LabelEditScreen(
	labelId: Int,
) {
	Column {
		Text(text = "Label Edit Screen")
		Text(text = "Label ID: $labelId")
		CustomLoadingIndicator()
	}
}

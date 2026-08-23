package com.filippochinni.inventoryapp.ui.screen.inventoryGroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.model.AbstractType
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomBooleanField
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomTextField
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomFormConfirmGroup
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryCreateFields
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryCreateUIState
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryCreateViewModel


@Composable
fun InventoryCreateScreen(
	onCancelNav: () -> Unit,
	onConfirmNav: () -> Unit,
	modifier: Modifier = Modifier,
	viewModel: InventoryCreateViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	when (val state = uiState) {
		is InventoryCreateUIState.Loading -> {
			CustomLoadingIndicator()
		}
		is InventoryCreateUIState.Error -> {
			//TODO
		}
		is InventoryCreateUIState.Success -> {
			InventoryCreateForm(
				onCancel = onCancelNav,
				onConfirm = {
					viewModel.sendForm(state.fields)
					onConfirmNav()
				},
				fields = state.fields,
				isValidated = state.isValidated,
				onValueChange = viewModel::updateUiState,
				modifier = modifier
			)
		}
	}
}

@Composable
fun InventoryCreateForm(
	onCancel : () -> Unit,
	onConfirm : () -> Unit,
	fields: InventoryCreateFields,
	isValidated: Boolean,
	onValueChange: (InventoryCreateFields) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.form_gap_ver)),
		modifier = modifier
			.padding(dimensionResource(R.dimen.screen_border_padding_hor))
	) {
		CustomTextField(
			value = fields.name,
			onValueChange = { onValueChange(fields.copy(name = it)) },
			label = R.string.inventory_details__name,
			maxLength = AbstractType.MAX_NAME_LENGTH,
			singleLine = true,
			modifier = Modifier
				.fillMaxWidth()
		)
		CustomTextField(
			value = fields.description,
			onValueChange = { onValueChange(fields.copy(description = it)) },
			label = R.string.common__details__description,
			maxLength = AbstractType.MAX_DESCRIPTION_LENGTH,
			singleLine = false,
			isLastField = true,
			modifier = Modifier
				.fillMaxWidth()
				.height(dimensionResource(R.dimen.form_elem_input_text_long_h))
		)
		CustomBooleanField(
			value = fields.isActive,
			onValueChange = { onValueChange(fields.copy(isActive = it)) },
			textContent = R.string.inventory_details__active__tip,
			modifier = Modifier
				.fillMaxWidth()
		)
		Spacer(modifier = Modifier.weight(1f))
		CustomFormConfirmGroup(
			enabled = isValidated,
			onCancel = onCancel,
			onConfirm = onConfirm,
			modifier = Modifier
		)
	}
}

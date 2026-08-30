package com.filippochinni.inventoryapp.ui.screen.labelsGroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.model.AbstractType
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomColorPickerField
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomFormConfirmGroup
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomTextField
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryCreateViewModel
import com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup.LabelCreateFields
import com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup.LabelCreateUIState
import com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup.LabelCreateViewModel


@Composable
fun LabelCreateScreen(
	onCancelNav: () -> Unit,
	onConfirmNav: () -> Unit,
	modifier: Modifier = Modifier,
	viewModel: LabelCreateViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	when (val state = uiState) {
		is LabelCreateUIState.Loading -> {
			CustomLoadingIndicator()
		}
		is LabelCreateUIState.Error -> {
			//TODO
		}
		is LabelCreateUIState.Success -> {
			LabelCreateForm(
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
fun LabelCreateForm(
	onCancel : () -> Unit,
	onConfirm : () -> Unit,
	fields: LabelCreateFields,
	isValidated: Boolean,
	onValueChange: (LabelCreateFields) -> Unit,
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
			label = R.string.label_details__name,
			maxLength = AbstractType.MAX_NAME_LENGTH,
			singleLine = true,
			modifier = Modifier.fillMaxWidth()
		)
		CustomColorPickerField(
			value = fields.color,
			onValueChange = { onValueChange(fields.copy(color = it)) },
			label = R.string.label_details__color,
			modifier = Modifier.fillMaxWidth()
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

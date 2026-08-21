package com.filippochinni.inventoryapp.ui.screen.inventoryGroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.model.AbstractType
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryCreateViewModel


@Composable
fun InventoryCreateScreen(
	onCancel : () -> Unit,
	onConfirm : () -> Unit,
	modifier: Modifier = Modifier,
	viewModel: InventoryCreateViewModel = hiltViewModel()
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.form_gap_ver)),
		modifier = modifier
			.padding(dimensionResource(R.dimen.screen_border_padding_hor))
	) {
		OutlinedTextField(
			value = "",
			onValueChange = {},
			label = { Text(stringResource(R.string.inventory_details__name)) },
			supportingText = { Row(
				horizontalArrangement = Arrangement.End,
				modifier = Modifier.fillMaxWidth()
			) {
				Text("${0}/${AbstractType.MAX_NAME_LENGTH}")
			} },
			singleLine = true,
			modifier = Modifier
				.fillMaxWidth()
		)
		OutlinedTextField(
			value = "",
			onValueChange = {},
			label = { Text(stringResource(R.string.entity_details__description)) },
			singleLine = false,
			modifier = Modifier
				.fillMaxWidth()
				.height(dimensionResource(R.dimen.form_elem_input_text_long_h))
		)
		ListItem(
			colors = ListItemDefaults.colors(
				containerColor = MaterialTheme.colorScheme.secondaryContainer
			),
			shapes = ListItemDefaults.shapes(
				shape = MaterialTheme.shapes.large
			),
			leadingContent = { Icon(
				painter = painterResource(id = R.drawable.icon_checkcircle_fill0),
				contentDescription = null
			)},
			trailingContent = { Checkbox(checked = false, onCheckedChange = {}) },
			modifier = Modifier
				.fillMaxWidth()
		) {
			Text(stringResource(R.string.inventory_details__active__tip))
		}
		Spacer(modifier = Modifier.weight(1f))
		Row(
			horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.button_group_gap_hor)),
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
		) {
			OutlinedButton(
				onClick = onCancel,
				modifier = Modifier
					.weight(1f)
			) {
				Text(stringResource(R.string.common__action_cancel))
			}
			Button(
				onClick = onConfirm,
				colors = ButtonDefaults.buttonColors(
					containerColor = MaterialTheme.colorScheme.primary
				),
				modifier = Modifier
					.weight(1f)
			) {
				Text(stringResource(R.string.common__action_confirm))
			}
		}
	}
}

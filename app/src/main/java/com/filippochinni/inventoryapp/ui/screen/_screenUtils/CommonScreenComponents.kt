package com.filippochinni.inventoryapp.ui.screen._screenUtils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.ToggleButtonShapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.filippochinni.inventoryapp.R


@Composable
fun CustomTextField(
	value: String,
	onValueChange: (String) -> Unit,
	@StringRes label: Int,
	maxLength: Int,
	singleLine: Boolean,
	isLastField: Boolean = false,
	modifier: Modifier = Modifier
) {
	val focusManager: FocusManager = LocalFocusManager.current

	OutlinedTextField(
		value = value,
		onValueChange = onValueChange,
		label = { Text(stringResource(label)) },
		supportingText = { Row(
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			if (value.length > maxLength) {
				Text(stringResource(R.string.common__error__max_length),)
			} else
				Text("")
			Text("${value.length}/$maxLength")
		} },
		singleLine = singleLine,
		isError = value.length > maxLength,
		keyboardOptions = KeyboardOptions(
			imeAction = if (isLastField) ImeAction.Done else ImeAction.Next
		),
		keyboardActions = KeyboardActions(
			onNext = { focusManager.moveFocus(FocusDirection.Next)},
			onDone = { focusManager.clearFocus() }
		),
		modifier = modifier
	)
}

@Composable
fun CustomBooleanField(
	value: Boolean,
	onValueChange: (Boolean) -> Unit,
	@StringRes textContent: Int,
	modifier: Modifier = Modifier
) {
	val color =
		if (value) MaterialTheme.colorScheme.secondaryContainer
		else MaterialTheme.colorScheme.surfaceContainer
	ListItem(
		colors = ListItemDefaults.colors(
			containerColor = color
		),
		shapes = ListItemDefaults.shapes(
			shape = MaterialTheme.shapes.large
		),
		leadingContent = { Icon(
			painter = painterResource(id = R.drawable.icon_checkcircle_fill0),
			contentDescription = null
		)},
		trailingContent = { Checkbox(checked = value, onCheckedChange = { onValueChange(it) }) },
		modifier = modifier
	) {
		Text(stringResource(textContent))
	}
}

@Composable
fun CustomLoadingIndicator() {
	Box(
		contentAlignment = Alignment.Center,
		modifier = Modifier
			.fillMaxSize()
	) {
		CircularProgressIndicator(
			modifier = Modifier
				.size(dimensionResource(R.dimen.loading_indicator_large))
		)
	}
}

@Composable
fun CustomFormConfirmGroup(
	enabled : Boolean,
	onCancel: () -> Unit,
	onConfirm: () -> Unit,
	@StringRes cancelText: Int = R.string.common__action__cancel,
	@StringRes confirmText: Int = R.string.common__action__confirm,
	modifier: Modifier = Modifier
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.button_group_gap_hor)),
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
			.fillMaxWidth()
	) {
		OutlinedButton(
			onClick = onCancel,
			modifier = Modifier
				.weight(1f)
		) {
			Text(stringResource(cancelText))
		}
		Button(
			enabled = enabled,
			onClick = onConfirm,
			colors = ButtonDefaults.buttonColors(
				containerColor = MaterialTheme.colorScheme.primary
			),
			modifier = Modifier
				.weight(1f)
		) {
			Text(stringResource(confirmText))
		}
	}
}

@Composable
fun CustomConnectedButtonsGroup(
	labelsList: List<Int>,
	iconNormalList: List<Int>,
	iconSelectedList: List<Int>,
	onCallbackList: List<() -> Unit>,
	modifier: Modifier = Modifier
) {
	var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

	Row(
		horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
		modifier = modifier
			.fillMaxWidth()
	) {
		labelsList.forEachIndexed { index, label ->
			ToggleButton(
				checked = selectedIndex == index,
				onCheckedChange = {
					selectedIndex = index
					onCallbackList[index]()
				},
				colors = ToggleButtonDefaults.toggleButtonColors(
					containerColor = MaterialTheme.colorScheme.secondaryContainer,
					checkedContainerColor = MaterialTheme.colorScheme.secondary,
					contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
					checkedContentColor = MaterialTheme.colorScheme.onSecondary
				),
				shapes = ToggleButtonShapes(
					shape = RoundedCornerShape(
						topStart =
							if (index == 0) dimensionResource(R.dimen.rounding_extra_extra_large)
							else dimensionResource(R.dimen.rounding_small),
						bottomStart =
							if (index == 0) dimensionResource(R.dimen.rounding_extra_extra_large)
							else dimensionResource(R.dimen.rounding_small),
						topEnd =
							if (index == labelsList.size - 1) dimensionResource(R.dimen.rounding_extra_extra_large)
							else dimensionResource(R.dimen.rounding_small),
						bottomEnd =
							if (index == labelsList.size - 1) dimensionResource(R.dimen.rounding_extra_extra_large)
							else dimensionResource(R.dimen.rounding_small)
					),
					checkedShape = RoundedCornerShape(
						topStart = dimensionResource(R.dimen.rounding_extra_extra_large),
						bottomStart = dimensionResource(R.dimen.rounding_extra_extra_large),
						topEnd = dimensionResource(R.dimen.rounding_extra_extra_large),
						bottomEnd = dimensionResource(R.dimen.rounding_extra_extra_large)
					),
					pressedShape = MaterialTheme.shapes.large
				),
				modifier = Modifier.weight(1f)
			) {
				Icon(
					painter = painterResource(
						if (selectedIndex == index) iconSelectedList[index]
						else iconNormalList[index],
					),
					contentDescription = null
				)
				Spacer(modifier = Modifier.size(ToggleButtonDefaults.IconSpacing))
				Text(stringResource(label))
			}
		}
	}
}

@Composable
fun CustomSectionHeader(
	@StringRes text: Int,
	modifier: Modifier = Modifier
) {
	Text(
		text = stringResource(text),
		style = MaterialTheme.typography.titleMedium,
		fontWeight = FontWeight.Bold,
		modifier = modifier
			.padding(horizontal = dimensionResource(R.dimen.padding_small))
	)
}

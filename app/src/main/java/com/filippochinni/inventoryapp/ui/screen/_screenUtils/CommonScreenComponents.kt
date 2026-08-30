package com.filippochinni.inventoryapp.ui.screen._screenUtils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.ToggleButtonShapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
fun CustomColorPickerField(
	value: Color,
	onValueChange: (Color) -> Unit,
	@StringRes label: Int,
	modifier: Modifier = Modifier
) {
	var colorPickerDialogOpen by rememberSaveable { mutableStateOf(false) }
	val containerColor =
		if (value == Color.Unspecified) MaterialTheme.colorScheme.surfaceContainer
		else value
	ListItem(
		colors = ListItemDefaults.colors(
			containerColor = containerColor
		),
		shapes = ListItemDefaults.shapes(
			shape = MaterialTheme.shapes.large
		),
		leadingContent = { Icon(
			painter = painterResource(id = R.drawable.icon_label_fill0),
			contentDescription = null
		) },
		trailingContent = {
			OutlinedIconButton(
				onClick = { colorPickerDialogOpen = true },
				colors = IconButtonDefaults.outlinedIconButtonColors(
					containerColor = MaterialTheme.colorScheme.secondaryContainer
				)
			) {
				Icon(
					painter = painterResource(id = R.drawable.icon_color),
					contentDescription = null,
					tint = MaterialTheme.colorScheme.onSecondaryContainer
				)
			}
		},
		modifier = modifier
	) {
		Text(stringResource(label))

		when {
			colorPickerDialogOpen -> {
				CustomColorPickerDialog(
					onConfirm = {
						onValueChange(it)
						colorPickerDialogOpen = false
					},
					onDismiss = { colorPickerDialogOpen = false },
					initialColor = containerColor
				)
			}
		}
	}
}

@Composable
fun CustomColorPickerDialog(
	onConfirm: (Color) -> Unit,
	onDismiss: () -> Unit,
	initialColor: Color = MaterialTheme.colorScheme.surfaceContainer,
	modifier: Modifier = Modifier
) {
	fun colorToHex(color: Color): String {
		val red = (color.red * 255).toInt()
		val green = (color.green * 255).toInt()
		val blue = (color.blue * 255).toInt()
		return String.format("#%02X%02X%02X", red, green, blue)
	}

	var red by rememberSaveable { mutableIntStateOf(initialColor.red.times(255).toInt()) }
	var green by rememberSaveable { mutableIntStateOf(initialColor.green.times(255).toInt()) }
	var blue by rememberSaveable { mutableIntStateOf(initialColor.blue.times(255).toInt()) }
	var hexInput by rememberSaveable { mutableStateOf(colorToHex(initialColor)) }
	var isHexError by rememberSaveable { mutableStateOf(false) }

	val currentColor = Color(red = red, green = green, blue = blue)

	fun updateColorFromHex(hex: String) {
		try {
			if (hex.isEmpty()) {
				isHexError = true
				return
			}
			val cleanHex = hex.removePrefix("#")
			if (cleanHex.length != 6) {
				isHexError = true
				return
			}
			val colorSplit = cleanHex.chunked(2).map { it.toInt(16) }
			red = colorSplit[0]
			green = colorSplit[1]
			blue = colorSplit[2]
			isHexError = false
		} catch (e: Exception) {
			isHexError = true
		}
	}

	fun updateHexFromSliders() {
		hexInput = colorToHex(currentColor)
		isHexError = false
	}

	AlertDialog(
		onDismissRequest = onDismiss,
		dismissButton = {
			TextButton(
				onClick = onDismiss
			) {
				Text(stringResource(R.string.common__action__cancel))
			}
		},
		confirmButton = {
			TextButton(
				onClick = { onConfirm(currentColor) },
				enabled = !isHexError
			) {
				Text(stringResource(R.string.common__action__confirm))
			}
		},
		title = {
			Text(stringResource(R.string.dialog__color_picker__title))
		},
		icon = {
			Icon(
				painter = painterResource(R.drawable.icon_color),
				contentDescription = null
			)
		},
		text = {
			Column(
				verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.form_gap_ver)),
				modifier = Modifier
					.fillMaxWidth()
			) {
				Surface(
					color = currentColor,
					shape = MaterialTheme.shapes.large,
					modifier = Modifier
						.fillMaxWidth()
						.height(dimensionResource(R.dimen.list_elem_h_small))
				) { }
				Slider(
					colors = SliderDefaults.colors(
						thumbColor = Color.Red,
						activeTrackColor = Color.Red,
						inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
						inactiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant,
					),
					value = red.toFloat(),
					onValueChange = {
						red = it.toInt()
						updateHexFromSliders()
					},
					valueRange = 0f..255f,
					steps = 255,
					modifier = Modifier.fillMaxWidth()
				)
				Slider(
					colors = SliderDefaults.colors(
						thumbColor = Color.Green,
						activeTrackColor = Color.Green,
						inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
						inactiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant,
					),
					value = green.toFloat(),
					onValueChange = {
						green = it.toInt()
						updateHexFromSliders()
					},
					valueRange = 0f..255f,
					steps = 255,
					modifier = Modifier.fillMaxWidth()
				)
				Slider(
					colors = SliderDefaults.colors(
						thumbColor = Color.Blue,
						activeTrackColor = Color.Blue,
						inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
						inactiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant,
					),
					value = blue.toFloat(),
					onValueChange = {
						blue = it.toInt()
						updateHexFromSliders()
					},
					valueRange = 0f..255f,
					steps = 255,
					modifier = Modifier.fillMaxWidth()
				)
				CustomTextField(
					value = hexInput,
					onValueChange = {
						hexInput = it.uppercase()
						if (hexInput.length == 7) {
							updateColorFromHex(hexInput)
						}
						else {
							isHexError = true
						}
					},
					label = R.string.dialog__color_picker__text_field_label_hex,
					singleLine = true,
					maxLength = 7,
					isLastField = true,
					modifier = Modifier
				)
			}
		},
		modifier = modifier

	)
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
	buttonsMetadata: List<Triple<Int, Int, Int>>,
	onCallbackList: List<() -> Unit>,
	modifier: Modifier = Modifier
) {
	var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

	Row(
		horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween),
		modifier = modifier
			.fillMaxWidth()
	) {
		buttonsMetadata.forEachIndexed { index, (label, iconNormal, iconSelected) ->
			ToggleButton(
				checked = (selectedIndex == index),
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
							if (index == buttonsMetadata.size - 1) dimensionResource(R.dimen.rounding_extra_extra_large)
							else dimensionResource(R.dimen.rounding_small),
						bottomEnd =
							if (index == buttonsMetadata.size - 1) dimensionResource(R.dimen.rounding_extra_extra_large)
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
						if (selectedIndex == index) iconSelected
						else iconNormal,
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
fun CustomFilterChipsGroup(
	chipsMetadata: List<Triple<Int, Int, Int>>,
	onCallbackList: List<() -> Unit>,
	modifier: Modifier = Modifier
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.button_group_gap_hor)),
		modifier = modifier.fillMaxWidth()
	) {
		chipsMetadata.forEachIndexed { index, (label, iconNormal, iconSelected) ->
			var selected by rememberSaveable { mutableStateOf(false) }
			FilterChip(
				selected = selected,
				colors = FilterChipDefaults.filterChipColors(
					selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
				),
				onClick = {
					onCallbackList[index]()
					selected = !selected
				},
				label = {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier.fillMaxWidth()
					) {
						Icon(
							painter = painterResource(
								if (selected) iconSelected else iconNormal
							),
							contentDescription = null,
							tint = if (selected)
								MaterialTheme.colorScheme.primary
								else MaterialTheme.colorScheme.onPrimaryContainer,
							modifier = Modifier
								.padding(end = dimensionResource(R.dimen.padding_extra_small))
						)
						Text(
							text = stringResource(label),
							softWrap = false,
							overflow = TextOverflow.Ellipsis
						)
					}
				},
				contentPadding = PaddingValues(0.dp),
				modifier = Modifier.weight(1f)
			)
		}
	}
}

@Composable
fun CustomListSelectionModeToolbar(
	selectedElements: Set<Int>,
	onEditClick: (Int) -> Unit,
	onDeleteClick: (Set<Int>) -> Unit,
	onSwitchActiveClick: ((Int) -> Unit)? = null,
	innerPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	HorizontalFloatingToolbar(
		expanded = true,
		modifier = modifier
			.padding(bottom = innerPadding.calculateBottomPadding() + dimensionResource(R.dimen.padding_small))
			.shadow(
				elevation = dimensionResource(R.dimen.elevation_medium),
				shape = CircleShape
			)
	) {
		IconButton(
			onClick = { onEditClick(selectedElements.first()) },
			enabled = selectedElements.size == 1) {
			Icon(
				painter = painterResource(R.drawable.icon_edit_fill0),
				contentDescription = null
			)
		}
		if (onSwitchActiveClick != null) {
			IconButton(
				onClick = { onSwitchActiveClick(selectedElements.first()) },
				enabled = selectedElements.size == 1
			) {
				Icon(
					painter = painterResource(R.drawable.icon_checkcircle_fill0),
					contentDescription = null
				)
			}
		}
		IconButton(
			onClick = { onDeleteClick(selectedElements) },
			enabled = true
		) {
			Icon(
				painter = painterResource(R.drawable.icon_delete_fill0),
				contentDescription = null
			)
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

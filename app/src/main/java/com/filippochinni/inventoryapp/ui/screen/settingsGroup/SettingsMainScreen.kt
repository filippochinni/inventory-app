package com.filippochinni.inventoryapp.ui.screen.settingsGroup

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomSectionHeader
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.LanguageOption
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.SettingsMainUIState
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.SettingsMainViewModel
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.ThemeOption


@Composable
fun SettingsMainScreen(
	onAboutClick: () -> Unit,
	viewModel: SettingsMainViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	when (uiState) {
		is SettingsMainUIState.Loading -> {
			CustomLoadingIndicator()
		}

		is SettingsMainUIState.Error -> {
			//TODO: Show error message
		}

		is SettingsMainUIState.Success -> {
			val successUIState = uiState as SettingsMainUIState.Success

			var themePickerOpen by rememberSaveable { mutableStateOf(false) }
			var languagePickerOpen by rememberSaveable { mutableStateOf(false) }

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.verticalScroll(rememberScrollState())
			) {
				Column(
					verticalArrangement = Arrangement.Top,
					modifier = Modifier.fillMaxWidth()
				) {
					Image(
						painter = painterResource(id = R.drawable.img_placeholder),
						contentDescription = null,
						contentScale = ContentScale.FillHeight,
						modifier = Modifier
							.aspectRatio(16f / 9f)
							.height(dimensionResource(R.dimen.settings_poster_h))
							.fillMaxWidth()
					)
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
						modifier = Modifier
							.fillMaxWidth()
							.padding(
								horizontal = dimensionResource(R.dimen.screen_border_padding_hor),
								vertical = dimensionResource(R.dimen.screen_border_padding_ver)
							)
					) {
						Image(
							painter = painterResource(id = R.drawable.img_placeholder),
							contentDescription = null,
							contentScale = ContentScale.Crop,
							modifier = Modifier
								.height(dimensionResource(R.dimen.user_avatar_medium))
								.aspectRatio(1f)
								.clip(shape = CircleShape)
						)
						Column(
							verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
							modifier = Modifier.weight(1f)
						) {
							Text(
								text = "<${stringResource(R.string.settings_main_screen__local_account)}>",
								style = MaterialTheme.typography.titleMedium,
								fontWeight = FontWeight.Bold,
								modifier = Modifier
							)
							Text(
								text = stringResource(R.string.settings_main_screen__offline_user),
								style = MaterialTheme.typography.bodyMedium,
								modifier = Modifier
							)
						}
					}
				}
				HorizontalDivider(Modifier.padding(horizontal = dimensionResource(R.dimen.screen_border_padding_hor)))

				Column(
					verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
					modifier = Modifier
						.fillMaxWidth()
						.padding(
							horizontal = dimensionResource(R.dimen.screen_border_padding_hor),
							vertical = dimensionResource(R.dimen.screen_border_padding_ver)
						)
				) {
					CustomSectionHeader(
						text = R.string.settings_main_screen__section_title__general,
						modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
					)
					Column(
						verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_gap_ver_small)),
						modifier = Modifier.fillMaxWidth()
					) {
						SettingsElem(
							icon = R.drawable.icon_language,
							title = R.string.settings_main_screen__elem__language_title,
							subtitle = R.string.settings_main_screen__elem__language_subtitle,
							onClick = { languagePickerOpen = true }
						)
						SettingsElem(
							icon = R.drawable.icon_darkmode_fill0,
							title = R.string.settings_main_screen__elem__theme_title,
							subtitle = R.string.settings_main_screen__elem__theme_subtitle,
							onClick = { themePickerOpen = true }
						)
					}

					CustomSectionHeader(
						text = R.string.settings_main_screen__section_title__data,
						modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
					)
					Column(
						verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_gap_ver_small)),
						modifier = Modifier.fillMaxWidth()
					) {
						SettingsElem(
							icon = R.drawable.icon_upload,
							title = R.string.settings_main_screen__elem__export_data_title,
							subtitle = R.string.settings_main_screen__elem__export_data_subtitle,
							onClick = {}
						)
						SettingsElem(
							icon = R.drawable.icon_download,
							title = R.string.settings_main_screen__elem__import_data_title,
							subtitle = R.string.settings_main_screen__elem__import_data_subtitle,
							onClick = {}
						)
					}

					CustomSectionHeader(
						text = R.string.settings_main_screen__section_title__cloud_data,
						modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
					)
					Column(
						verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_gap_ver_small)),
						modifier = Modifier.fillMaxWidth()
					) {
						SettingsElem(
							icon = R.drawable.icon_cloudupload,
							title = R.string.settings_main_screen__elem__save_cloud_title,
							subtitle = R.string.settings_main_screen__elem__save_cloud_subtitle,
							onClick = {},
							enabled = false //TODO: Enable when cloud save is implemented
						)
						SettingsElem(
							icon = R.drawable.icon_cloudload,
							title = R.string.settings_main_screen__elem__load_cloud_title,
							subtitle = R.string.settings_main_screen__elem__load_cloud_subtitle,
							onClick = {},
							enabled = false //TODO: Enable when cloud load is implemented
						)
					}

					CustomSectionHeader(
						text = R.string.settings_main_screen__section_title__about,
						modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
					)
					Column(
						verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_gap_ver_small)),
						modifier = Modifier.fillMaxWidth()
					) {
						SettingsElem(
							icon = R.drawable.icon_info_fill0,
							title = R.string.settings_main_screen__elem__about_title,
							subtitle = R.string.settings_main_screen__elem__about_subtitle,
							onClick = onAboutClick
						)
					}
				}
			}

			when {
				languagePickerOpen -> {
					LanguagePickerDialog(
						onConfirm = {
							languagePickerOpen = false
							viewModel.selectLanguage(it)
						},
						onDismiss = { languagePickerOpen = false },
						currentLanguage = successUIState.selectedLanguage
					)
				}
				themePickerOpen -> {
					ThemePickerDialog(
						onConfirm = {
							themePickerOpen = false
							viewModel.selectTheme(it)
						},
						onDismiss = { themePickerOpen = false },
						currentTheme = successUIState.selectedTheme
					)
				}
			}
		}
	}
}

@Composable
fun SettingsElem(
	@DrawableRes icon: Int,
	@StringRes title: Int,
	@StringRes subtitle: Int,
	onClick: () -> Unit,
	enabled: Boolean = true
) {
	ListItem(
		onClick = onClick,
		enabled = enabled,
		colors = ListItemDefaults.colors(
			containerColor = MaterialTheme.colorScheme.surfaceContainer,
			disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest
		),
		shapes = ListItemDefaults.shapes(
			shape = MaterialTheme.shapes.large
		),
		leadingContent = {
			Icon(
				painter = painterResource(icon),
				contentDescription = null
			)
		},
		modifier = Modifier
			.fillMaxWidth()
			.height(dimensionResource(R.dimen.list_elem_h_small))
	) {
		Column(
			verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_extra_small)),
			modifier = Modifier
		) {
			Text(
				text = stringResource(title),
				style = MaterialTheme.typography.bodyLarge,
				fontWeight = FontWeight.Bold
			)
			Text(
				text = stringResource(subtitle),
				style = MaterialTheme.typography.bodyMedium
			)
		}
	}
}

@Composable
fun ThemePickerDialog(
	onConfirm: (ThemeOption) -> Unit,
	onDismiss: () -> Unit,
	currentTheme: ThemeOption
) {
	var selectedTheme by rememberSaveable { mutableStateOf(currentTheme) }

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
				onClick = { onConfirm(selectedTheme) },
			) {
				Text(stringResource(R.string.common__action__confirm))
			}
		},
		title = { Text(stringResource(R.string.dialog__theme_picker__title)) },
		icon = {
			Icon(
				painter = painterResource(R.drawable.icon_darkmode_fill0),
				contentDescription = null
			)
		},
		text = {
			Column(
				verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
				modifier = Modifier.fillMaxWidth()
			) {
				ThemeOption.entries.forEach { theme ->
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
						modifier = Modifier
							.fillMaxWidth()
							.clickable { selectedTheme = theme }
					) {
						RadioButton(
							selected = selectedTheme == theme,
							onClick = { selectedTheme = theme }
						)
						Text(
							text = stringResource(theme.themeName),
							style = MaterialTheme.typography.bodyLarge
						)
					}
				}
			}
		},
	)
}

@Composable
fun LanguagePickerDialog(
	onConfirm: (LanguageOption) -> Unit,
	onDismiss: () -> Unit,
	currentLanguage: LanguageOption
) {
	var selectedLanguage by rememberSaveable { mutableStateOf(currentLanguage) }

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
				onClick = { onConfirm(selectedLanguage) },
			) {
				Text(stringResource(R.string.common__action__confirm))
			}
		},
		title = { Text(stringResource(R.string.dialog__language_picker__title)) },
		icon = {
			Icon(
				painter = painterResource(R.drawable.icon_language),
				contentDescription = null
			)
		},
		text = {
			Column(
				verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
				modifier = Modifier.fillMaxWidth()
			) {
				LanguageOption.entries.forEach { language ->
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
						modifier = Modifier
							.fillMaxWidth()
							.clickable { selectedLanguage = language }
					) {
						RadioButton(
							selected = selectedLanguage == language,
							onClick = { selectedLanguage = language }
						)
						Text(
							text = stringResource(language.languageName),
							style = MaterialTheme.typography.bodyLarge
						)
					}
				}
			}
		},
	)
}

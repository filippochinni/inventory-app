package com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippochinni.inventoryapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


sealed interface SettingsMainUIState {
	object Loading : SettingsMainUIState

	data class Error(val error: String) : SettingsMainUIState

	data class Success(
		val selectedTheme: ThemeOption,
		val selectedLanguage: LanguageOption
	) : SettingsMainUIState
}

enum class ThemeOption(@StringRes val themeName: Int) {
	LIGHT(R.string.settings_main_screen__option__theme_light),
	DARK(R.string.settings_main_screen__option__theme_dark),
	SYSTEM(R.string.settings_main_screen__option__theme_system)
}

enum class LanguageOption(@StringRes val languageName: Int) {
	ENGLISH(R.string.settings_main_screen__option__language_english),
	ITALIAN(R.string.settings_main_screen__option__language_italian)
}

@HiltViewModel
class SettingsMainViewModel @Inject constructor() : ViewModel() {
	private val selectedTheme: MutableStateFlow<ThemeOption> = MutableStateFlow(ThemeOption.SYSTEM)
	private val selectedLanguage: MutableStateFlow<LanguageOption> = MutableStateFlow(LanguageOption.ENGLISH)

	val uiState: StateFlow<SettingsMainUIState> = combine(
		selectedTheme, selectedLanguage
	) { theme, language ->
		SettingsMainUIState.Success(
			selectedTheme = theme,
			selectedLanguage = language
		)
	}.stateIn(
		scope = viewModelScope,
		initialValue = SettingsMainUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

	fun selectTheme(theme: ThemeOption) {
		selectedTheme.update { theme }
	}

	fun selectLanguage(language: LanguageOption) {
		selectedLanguage.update { language }
	}

}

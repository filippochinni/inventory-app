package com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface SettingsMainUIState {
	object Loading : SettingsMainUIState

	data class Success(
		val placeholder: Nothing
	) : SettingsMainUIState

	data class Error(val error: String) : SettingsMainUIState
}

@HiltViewModel
class SettingsMainViewModel @Inject constructor() : ViewModel() {
	private val placeholder: Flow<Nothing> = TODO() //TODO

	val uiState: StateFlow<SettingsMainUIState> = placeholder.map {
		SettingsMainUIState.Success(it)
	}.stateIn(
		scope = viewModelScope,
		initialValue = SettingsMainUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

}

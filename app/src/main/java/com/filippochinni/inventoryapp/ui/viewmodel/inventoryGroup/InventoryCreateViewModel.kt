package com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface InventoryCreateUIState {
	object Loading : InventoryCreateUIState

	data class Success(
		val placeholder: Nothing
	) : InventoryCreateUIState

	data class Error(val error: String) : InventoryCreateUIState
}

@HiltViewModel
class InventoryCreateViewModel @Inject constructor() : ViewModel() {
	private val placeholder: Flow<Nothing> = TODO() //TODO

	val uiState: StateFlow<InventoryCreateUIState> = placeholder.map {
		InventoryCreateUIState.Success(it)
	}.stateIn(
		scope = viewModelScope,
		initialValue = InventoryCreateUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

}

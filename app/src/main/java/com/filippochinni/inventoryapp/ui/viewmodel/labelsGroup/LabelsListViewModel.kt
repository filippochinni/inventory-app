package com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface LabelsListUIState {
	object Loading : LabelsListUIState

	data class Success(
		val placeholder: Nothing
	) : LabelsListUIState

	data class Error(val error: String) : LabelsListUIState
}

@HiltViewModel
class LabelsListViewModel @Inject constructor() : ViewModel() {
	private val placeholder: Flow<Nothing> = TODO() //TODO

	val uiState: StateFlow<LabelsListUIState> = placeholder.map {
		LabelsListUIState.Success(it)
	}.stateIn(
		scope = viewModelScope,
		initialValue = LabelsListUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

}

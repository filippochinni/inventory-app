package com.filippochinni.inventoryapp.ui.viewmodel.searchGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface SearchMainUIState {
	object Loading : SearchMainUIState

	data class Success(
		val placeholder: Nothing
	) : SearchMainUIState

	data class Error(val error: String) : SearchMainUIState
}

@HiltViewModel
class SearchMainViewModel @Inject constructor() : ViewModel() {
	private val placeholder: Flow<Nothing> = TODO() //TODO

	val uiState: StateFlow<SearchMainUIState> = placeholder.map {
		SearchMainUIState.Success(it)
	}.stateIn(
		scope = viewModelScope,
		initialValue = SearchMainUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

}

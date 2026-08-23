package com.filippochinni.inventoryapp.ui.viewmodel.statisticsGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface StatisticsMainUIState {
	object Loading : StatisticsMainUIState

	data class Error(val error: String) : StatisticsMainUIState

	data class Success(
		val placeholder: Nothing?,
		val selectedTab: StatisticsTab
	) : StatisticsMainUIState
}

enum class StatisticsTab {
	ITEMS,
	ARCHIVED,
	SOLD
}

@HiltViewModel
class StatisticsMainViewModel @Inject constructor() : ViewModel() {
	private val placeholder: Flow<Nothing?> = flow { emit(null) } //TODO

	val uiState: StateFlow<StatisticsMainUIState> = placeholder.map {
		StatisticsMainUIState.Success(it, StatisticsTab.ITEMS)
	}.stateIn(
		scope = viewModelScope,
		initialValue = StatisticsMainUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

}

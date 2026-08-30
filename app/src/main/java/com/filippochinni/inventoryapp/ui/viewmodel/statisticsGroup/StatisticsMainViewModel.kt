package com.filippochinni.inventoryapp.ui.viewmodel.statisticsGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippochinni.inventoryapp.ui.screen._screenUtils.mockStatisticsList
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


sealed interface StatisticsMainUIState {
	object Loading : StatisticsMainUIState

	data class Error(val error: String) : StatisticsMainUIState

	data class Success(
		val itemsStats: List<String>,
		val archivedStats: List<String>,
		val soldStats: List<String>,
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

	private val itemsStats: Flow<List<String>> = mockStatisticsList(0) //TODO
	private val archivedStats: Flow<List<String>> = mockStatisticsList(1) //TODO
	private val soldStats: Flow<List<String>> = mockStatisticsList(2) //TODO

	private val selectedTab: MutableStateFlow<StatisticsTab> = MutableStateFlow(StatisticsTab.ITEMS)

	val uiState: StateFlow<StatisticsMainUIState> = combine(
		itemsStats, archivedStats, soldStats, selectedTab
	) { items, archived, sold, tab ->
		StatisticsMainUIState.Success(
			itemsStats = items,
			archivedStats = archived,
			soldStats = sold,
			selectedTab = tab
		)
	}.stateIn(
		scope = viewModelScope,
		initialValue = StatisticsMainUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

	fun selectTab(tab: StatisticsTab) {
		selectedTab.update { tab }
	}

}

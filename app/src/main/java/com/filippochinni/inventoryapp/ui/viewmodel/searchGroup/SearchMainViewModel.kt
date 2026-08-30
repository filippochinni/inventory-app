package com.filippochinni.inventoryapp.ui.viewmodel.searchGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlin.collections.emptyList


sealed interface SearchMainUIState {
	object Loading : SearchMainUIState

	data class Error(val error: String) : SearchMainUIState

	data class Success(
		val searchQuery: String,
		val selectedTab: SearchTab,
		val selectedLabels: MutableList<Int>
	) : SearchMainUIState
}

enum class SearchTab {
	ALL,
	ITEMS,
	PLACES
}

@HiltViewModel
class SearchMainViewModel @Inject constructor() : ViewModel() {
	private val searchQuery: MutableStateFlow<String> = MutableStateFlow("")

	private val selectedTab: MutableStateFlow<SearchTab> = MutableStateFlow(SearchTab.ALL)

	private val selectedLabels: MutableStateFlow<MutableList<Int>> = MutableStateFlow(emptyList<Int>().toMutableList())


	val uiState: StateFlow<SearchMainUIState> = combine(
		searchQuery, selectedTab, selectedLabels
	) { query, tab, labels ->
		SearchMainUIState.Success(
			searchQuery = query,
			selectedTab = tab,
			selectedLabels = labels
		)
	}.stateIn(
		scope = viewModelScope,
		initialValue = SearchMainUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

	fun selectTab(tab: SearchTab) {
		selectedTab.update { tab }
	}

	fun search(query: String) {
		searchQuery.update { query }
	}

}

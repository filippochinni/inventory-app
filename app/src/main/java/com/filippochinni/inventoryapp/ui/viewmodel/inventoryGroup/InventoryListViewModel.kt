package com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippochinni.inventoryapp.model.Inventory
import com.filippochinni.inventoryapp.ui.screen._screenUtils.mockInventoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


sealed interface InventoryListUIState {
	object Loading : InventoryListUIState

	data class Error(val error: String) : InventoryListUIState

	data class Success(
		val inventoryList: List<Inventory>,
		val selectedElements: Set<Int>
	) : InventoryListUIState
}

@HiltViewModel
class InventoryListViewModel @Inject constructor() : ViewModel() {

	private val inventoryList: Flow<List<Inventory>> = mockInventoryList(6)    //TODO: useCaseReadInventory

	private val selectedElements = MutableStateFlow<Set<Int>>(emptySet())

	val uiState: StateFlow<InventoryListUIState> = combine(
		inventoryList, selectedElements) { inventoryList, selectedElements ->
		InventoryListUIState.Success(inventoryList, selectedElements)
	}.stateIn(
		scope = viewModelScope,
		initialValue = InventoryListUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

	fun switchActiveInventory(inventoryId: Int) {

		//TODO: useCaseReadInventory
	}

	fun deleteSelectedInventories(inventoryIdList: Set<Int>) {

		//TODO: useCaseDeleteInventory
	}

	fun toggleSelection(inventoryId: Int) {
		selectedElements.update { current ->
			if (current.contains(inventoryId))
				current.minus(inventoryId)
			else
				current.plus(inventoryId)
		}
	}

	fun clearSelection() {
		selectedElements.value = emptySet()
	}

}

package com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippochinni.inventoryapp.model.Inventory
import com.filippochinni.inventoryapp.ui.screen.mockInventoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


sealed interface InventoryListUIState {
	object Loading : InventoryListUIState

	data class Success(
		val inventoryList: List<Inventory>
	) : InventoryListUIState

	data class Error(val error: String) : InventoryListUIState
}

@HiltViewModel
class InventoryListViewModel @Inject constructor() : ViewModel() {

	private val inventoryList: Flow<List<Inventory>> = mockInventoryList(6)    //TODO: useCaseCRUDInventory

	val uiState: StateFlow<InventoryListUIState> = inventoryList.map {
		InventoryListUIState.Success(it)
	}.stateIn(
		scope = viewModelScope,
		initialValue = InventoryListUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)
}

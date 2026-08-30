package com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippochinni.inventoryapp.model.Inventory
import com.filippochinni.inventoryapp.model.Label
import com.filippochinni.inventoryapp.ui.screen._screenUtils.mockInventoryList
import com.filippochinni.inventoryapp.ui.screen._screenUtils.mockLabelList
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryListUIState
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


sealed interface LabelsListUIState {
	object Loading : LabelsListUIState

	data class Error(val error: String) : LabelsListUIState

	data class Success(
		val labelList: List<Label>,
		val selectedElements: Set<Int>
	) : LabelsListUIState
}

@HiltViewModel
class LabelsListViewModel @Inject constructor() : ViewModel() {

	private val labelList: Flow<List<Label>> = mockLabelList(17)    //TODO: useCaseReadLabel

	private val selectedElements: MutableStateFlow<Set<Int>> = MutableStateFlow(emptySet())

	val uiState: StateFlow<LabelsListUIState> = combine(
		labelList, selectedElements) { labelList, selectedElements ->
		LabelsListUIState.Success(labelList, selectedElements)
	}.stateIn(
		scope = viewModelScope,
		initialValue = LabelsListUIState.Loading,
		started = SharingStarted.WhileSubscribed(5000)
	)

	fun toggleSelection(labelId: Int) {
		selectedElements.update { current ->
			if (current.contains(labelId))
				current.minus(labelId)
			else
				current.plus(labelId)
		}
	}

	fun clearSelection() {
		selectedElements.value = emptySet()
	}

	fun deleteSelectedLabels(labelIdList: Set<Int>) {
		//TODO: useCaseDeleteLabel
	}

}

package com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup

import android.util.Log
import androidx.compose.ui.graphics.Path.Companion.combine
import androidx.compose.ui.text.style.TextDecoration.Companion.combine
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filippochinni.inventoryapp.ui.screen._screenUtils.notImplementedToast
import com.filippochinni.inventoryapp.ui.viewmodel.statisticsGroup.StatisticsMainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


sealed interface InventoryCreateUIState {
	object Loading : InventoryCreateUIState

	data class Error(val error: String) : InventoryCreateUIState

	data class Success(
		val fields: InventoryCreateFields = InventoryCreateFields(),
		val isValidated: Boolean
	) : InventoryCreateUIState
}

data class InventoryCreateFields(
	val name: String = "",
	val description: String = "",
	val isActive: Boolean = false
)

@HiltViewModel
class InventoryCreateViewModel @Inject constructor() : ViewModel() {

	private val _uiState = MutableStateFlow<InventoryCreateUIState>(InventoryCreateUIState.Loading)
	val uiState: StateFlow<InventoryCreateUIState> = _uiState.asStateFlow()

	init {
		_uiState.value = InventoryCreateUIState.Success(
			fields = InventoryCreateFields(),
			isValidated = false
		)
	}

	fun updateUiState(fields: InventoryCreateFields) {
		_uiState.update {
			InventoryCreateUIState.Success(
				fields = fields,
				isValidated = validateFields(fields)
			)
		}
	}

	fun sendForm(fields: InventoryCreateFields) {
		//TODO: Domain layer
		Log.d("InventoryCreateViewModel", "sendForm: $fields")
	}

	fun validateFields(fields: InventoryCreateFields): Boolean {	//TODO move to Domain layer
		return fields.name.isNotBlank() && fields.description.isNotBlank()
	}

}

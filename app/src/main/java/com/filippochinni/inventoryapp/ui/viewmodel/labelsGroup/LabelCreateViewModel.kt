package com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryCreateFields
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


sealed interface LabelCreateUIState {
	object Loading : LabelCreateUIState

	data class Error(val error: String) : LabelCreateUIState

	data class Success(
		val fields: LabelCreateFields = LabelCreateFields(),
		val isValidated: Boolean
	) : LabelCreateUIState
}

data class LabelCreateFields(
	val name: String = "",
	val color: Color = Color.Unspecified,
)


@HiltViewModel
class LabelCreateViewModel @Inject constructor() : ViewModel() {

	private val _uiState = MutableStateFlow<LabelCreateUIState>(LabelCreateUIState.Loading)
	val uiState: StateFlow<LabelCreateUIState> = _uiState.asStateFlow()

	init {
		_uiState.value = LabelCreateUIState.Success(
			fields = LabelCreateFields(),
			isValidated = false
		)
	}

	fun updateUiState(fields: LabelCreateFields) {
		_uiState.value = LabelCreateUIState.Success(
			fields = fields,
			isValidated = validateFields(fields)
		)
	}

	fun sendForm(fields: LabelCreateFields) {
		//TODO: domain layer
		Log.d("LabelCreateViewModel", "sendForm: $fields")
	}

	fun validateFields(fields: LabelCreateFields): Boolean {	//TODO move to Domain layer
		return fields.name.isNotBlank() && fields.color != Color.Unspecified
	}
}

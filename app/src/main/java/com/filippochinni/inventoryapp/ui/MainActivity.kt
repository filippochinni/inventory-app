package com.filippochinni.inventoryapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.ui.theme.InventoryAppTheme
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.SettingsMainUIState
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.SettingsMainViewModel
import com.filippochinni.inventoryapp.ui.viewmodel.settingsGroup.ThemeOption
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val viewModel: SettingsMainViewModel = hiltViewModel()
			val uiState by viewModel.uiState.collectAsStateWithLifecycle()
			val themeMode = when (uiState) {
				is SettingsMainUIState.Success -> (uiState as SettingsMainUIState.Success).selectedTheme
				else -> ThemeOption.LIGHT
			} //TODO refactor this after implementing DataStore
			InventoryAppTheme(themeMode = themeMode) {
				Surface(modifier = Modifier.fillMaxSize()) {
					InventoryApp()
				}
			}
		}
	}
}

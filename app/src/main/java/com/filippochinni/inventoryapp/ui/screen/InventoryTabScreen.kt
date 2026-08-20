package com.filippochinni.inventoryapp.ui.screen

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.viewmodel.InventoryViewModel


@Composable
fun InventoryTabScreen(
	onFABClick: () -> Unit,
	modifier: Modifier = Modifier,
	viewModel: InventoryViewModel = hiltViewModel()
) {
	Scaffold(
		floatingActionButton = {
			FloatingActionButton(
				onClick = onFABClick,
				modifier = modifier
			) {
				Icon(
					painter = painterResource(R.drawable.icon_add),
					contentDescription = null
				)
			}
		}
	) { innerPadding ->
		innerPadding
	}
}

@Composable
fun InventoryListScreen(

	modifier: Modifier = Modifier,
) {

}


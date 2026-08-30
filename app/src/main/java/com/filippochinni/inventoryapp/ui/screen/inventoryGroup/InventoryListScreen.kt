package com.filippochinni.inventoryapp.ui.screen.inventoryGroup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.model.Inventory
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomListSelectionModeToolbar
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryListUIState
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryListViewModel


@Composable
fun InventoryListScreen(
	onFABClick: () -> Unit,
	onElementClick: (Inventory) -> Unit,
	onEditClick: (Int) -> Unit,
	modifier: Modifier = Modifier,
	viewModel: InventoryListViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
		when (uiState) {
			is InventoryListUIState.Loading -> {
				CustomLoadingIndicator()
			}
			is InventoryListUIState.Error -> {
				//TODO: Error State UI
			}
			is InventoryListUIState.Success -> {
				val successUIState = uiState as InventoryListUIState.Success
				val isSelectionMode = successUIState.selectedElements.isNotEmpty()

				BackHandler(enabled = isSelectionMode) {
					viewModel.clearSelection()
				}

				Box(modifier = Modifier) {
					InventoryList(
						inventoryList = successUIState.inventoryList,
						selectedElements = successUIState.selectedElements,
						onElementClick = { inventory: Inventory ->
							if (isSelectionMode) viewModel.toggleSelection(inventory.id)
							else onElementClick(inventory)
						},
						onLongPress = { inventory: Inventory -> viewModel.toggleSelection(inventory.id) },
						innerPadding = innerPadding,
						modifier = Modifier
					)

					if (isSelectionMode) {
						CustomListSelectionModeToolbar(
							selectedElements = successUIState.selectedElements,
							onEditClick = onEditClick,
							onSwitchActiveClick = viewModel::switchActiveInventory,
							onDeleteClick = viewModel::deleteSelectedInventories,
							innerPadding = innerPadding,
							modifier = Modifier
								.align(Alignment.BottomCenter)
						)
					}
				}
			}
		}
	}
}

@Composable
fun InventoryList(
	inventoryList: List<Inventory>,
	selectedElements: Set<Int>,
	onElementClick: (Inventory) -> Unit,
	onLongPress: (Inventory) -> Unit,
	innerPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	LazyColumn(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_gap_ver)),
		contentPadding = PaddingValues(
			start = dimensionResource(R.dimen.screen_border_padding_hor),
			end = dimensionResource(R.dimen.screen_border_padding_hor),
			top = dimensionResource(R.dimen.screen_border_padding_ver),
			bottom = dimensionResource(R.dimen.screen_border_padding_ver)
		),
		modifier = modifier
	) {
		if (inventoryList.isEmpty()) {
			item {
				Surface(
					color = MaterialTheme.colorScheme.surfaceContainerHigh,
					shape = MaterialTheme.shapes.extraLarge,
					modifier = Modifier
						.fillMaxWidth()
						.height(dimensionResource(R.dimen.info_surface_large))
				) {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						verticalArrangement = Arrangement.Center,
						modifier = Modifier
							.fillMaxHeight()
							.padding(dimensionResource(R.dimen.padding_medium))
					) {
						Text(
							text = stringResource(R.string.inventory_main_screen__no_inventories_title),
							style = MaterialTheme.typography.titleLarge,
							fontWeight = FontWeight.Bold,
							modifier = Modifier
						)
						Spacer(modifier = Modifier
							.height(dimensionResource(R.dimen.padding_large)))
						Text(
							text = stringResource(R.string.inventory_main_screen__no_inventories_subtitle),
							style = MaterialTheme.typography.bodyMedium,
							modifier = Modifier
						)
					}
				}
			}
		}
		else {
			items(inventoryList.size) { index ->
				val inventoryElem = inventoryList[index]
				InventoryElemCard(
					inventoryElem = inventoryElem,
					isSelected = selectedElements.contains(inventoryElem.id),
					onElementClick = onElementClick,
					onLongPress = onLongPress,
					modifier = Modifier
				)
			}
		}
	}
}

@Composable
fun InventoryElemCard(
	inventoryElem: Inventory,
	onElementClick: (Inventory) -> Unit,
	onLongPress: (Inventory) -> Unit,
	isSelected: Boolean,
	modifier: Modifier = Modifier,
) {
	ElevatedCard(
		elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_medium)),
		modifier = modifier
			.fillMaxWidth()
			.height(dimensionResource(R.dimen.list_elem_h_medium))
			.combinedClickable(
				onClick = { onElementClick(inventoryElem) },
				onLongClick = { onLongPress(inventoryElem) }
			)
			.then(
				if (inventoryElem.isActive) {
					Modifier.border(
						width = dimensionResource(R.dimen.border_medium),
						color = MaterialTheme.colorScheme.primary,
						shape = MaterialTheme.shapes.medium
					)
				} else {
					Modifier
				}
			)
	) {
		Box(
			modifier = Modifier.fillMaxSize()
		) {
			Row(
				modifier = Modifier.fillMaxSize()
			) {
				Column(
					horizontalAlignment = Alignment.Start,
					verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
					modifier = Modifier
						.fillMaxWidth()
						.weight(1f)
						.padding(dimensionResource(R.dimen.padding_medium))
				) {
					Text(
						text = inventoryElem.name,
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.Bold,
						modifier = Modifier
					)
					Text(
						text = inventoryElem.description,
						style = MaterialTheme.typography.bodyLarge,
						overflow = TextOverflow.Ellipsis,
						modifier = Modifier
					)
				}
				Box(
					modifier = Modifier
						.fillMaxHeight()
						.aspectRatio(1f)
				) {
					Image(
						painter = painterResource(R.drawable.img_placeholder),
						contentDescription = null,
						contentScale = ContentScale.Crop,
						modifier = Modifier
							.fillMaxSize()
					)
				}
			}
			if (isSelected) {
				InventoryElemCardOverlay(modifier = Modifier)
			}
		}
	}
}

@Composable
fun InventoryElemCardOverlay(
	modifier: Modifier = Modifier
) {
	Surface(
		color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
		shape = MaterialTheme.shapes.medium,
		modifier = modifier
			.fillMaxSize()
	) {
		Row(
			modifier = Modifier.fillMaxSize()
		) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.weight(1f)
			)
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.fillMaxHeight()
					.aspectRatio(1f)
			) {
				Icon(
					painter = painterResource(R.drawable.icon_checkcircle_fill1),
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
					modifier = Modifier
						.size(dimensionResource(R.dimen.icon_size_large))
				)
			}
		}
	}
}

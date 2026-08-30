package com.filippochinni.inventoryapp.ui.screen.labelsGroup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.model.Inventory
import com.filippochinni.inventoryapp.model.Label
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomListSelectionModeToolbar
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.screen.inventoryGroup.InventoryElemCard
import com.filippochinni.inventoryapp.ui.viewmodel.inventoryGroup.InventoryListUIState
import com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup.LabelsListUIState
import com.filippochinni.inventoryapp.ui.viewmodel.labelsGroup.LabelsListViewModel
import kotlin.random.Random


@Composable
fun LabelsListScreen(
	onFABClick: () -> Unit,
	onElementClick: (Label) -> Unit,
	onEditClick: (Int) -> Unit,
	modifier: Modifier = Modifier,
	viewModel: LabelsListViewModel = hiltViewModel()
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
			is LabelsListUIState.Loading -> {
				CustomLoadingIndicator()
			}

			is LabelsListUIState.Error -> {
				//TODO: Error State UI
			}

			is LabelsListUIState.Success -> {
				val successUIState = uiState as LabelsListUIState.Success
				val isSelectionMode = successUIState.selectedElements.isNotEmpty()

				BackHandler(enabled = isSelectionMode) {
					viewModel.clearSelection()
				}

				Box(modifier = Modifier) {
					LabelsList(
						labelList = successUIState.labelList,
						selectedElements = successUIState.selectedElements,
						onElementClick = { label: Label ->
							if (isSelectionMode) viewModel.toggleSelection(label.id)
							else onElementClick(label)
						},
						onLongPress = { label: Label -> viewModel.toggleSelection(label.id) },
						innerPadding = innerPadding,
						modifier = Modifier
					)

					if (isSelectionMode) {
						CustomListSelectionModeToolbar(
							selectedElements = successUIState.selectedElements,
							onEditClick = onEditClick,
							onDeleteClick = viewModel::deleteSelectedLabels,
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
fun LabelsList(
	labelList: List<Label>,
	selectedElements: Set<Int>,
	onElementClick: (Label) -> Unit,
	onLongPress: (Label) -> Unit,
	innerPadding: PaddingValues,
	modifier: Modifier = Modifier
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
		if (labelList.isEmpty()) {
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
							text = stringResource(R.string.label_main_screen__no_labels_title),
							style = MaterialTheme.typography.titleLarge,
							fontWeight = FontWeight.Bold,
							modifier = Modifier
						)
						Spacer(modifier = Modifier
							.height(dimensionResource(R.dimen.padding_large)))
						Text(
							text = stringResource(R.string.label_main_screen__no_labels_subtitle),
							style = MaterialTheme.typography.bodyMedium,
							modifier = Modifier
						)
					}
				}
			}
		}
		else {
			items(labelList.size) { index ->
				val labelElem = labelList[index]
				LabelElemListItem(
					labelElem = labelElem,
					labelUsage = Random.nextInt(0,100), //TODO: Replace with actual usage count
					onElementClick = onElementClick,
					onLongPress = onLongPress,
					isSelected = selectedElements.contains(labelElem.id),
					isSelectionMode = selectedElements.isNotEmpty(),
					modifier = Modifier
				)
			}
		}
	}
}

@Composable
fun LabelElemListItem(
	labelElem: Label,
	labelUsage: Int,
	onElementClick: (Label) -> Unit,
	onLongPress: (Label) -> Unit,
	isSelected: Boolean,
	isSelectionMode: Boolean,
	modifier: Modifier = Modifier
) {
	ListItem(
		colors = ListItemDefaults.colors(
			containerColor = labelElem.color
		),
		leadingContent = {
			if (isSelectionMode) {
				Checkbox(
					checked = isSelected,
					onCheckedChange = { onElementClick(labelElem) },
					modifier = Modifier
				)
			} else {
				Icon(
					painter = painterResource(R.drawable.icon_label_fill0),
					contentDescription = null,
					modifier = Modifier
				)
			}
		},
		trailingContent = {
			Text(
				text = stringResource(R.string.label_details__uses, labelUsage),
				style = MaterialTheme.typography.bodyLarge,
				modifier = Modifier
			)
		},
		shapes = ListItemDefaults.shapes(
			shape = MaterialTheme.shapes.large
		),
		modifier = Modifier
			.fillMaxWidth()
			.combinedClickable(
				onClick = { onElementClick(labelElem) },
				onLongClick = { onLongPress(labelElem) }
			)
	) {
		Text(
			text = labelElem.name,
			modifier = Modifier
		)
	}
}

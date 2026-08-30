package com.filippochinni.inventoryapp.ui.screen.searchGroup

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExpandedDockedSearchBar
import androidx.compose.material3.ExpandedDockedSearchBarWithGap
import androidx.compose.material3.ExpandedFullScreenContainedSearchBar
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.model.Label
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomConnectedButtonsGroup
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomFilterChipsGroup
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.viewmodel.searchGroup.SearchMainUIState
import com.filippochinni.inventoryapp.ui.viewmodel.searchGroup.SearchMainViewModel
import com.filippochinni.inventoryapp.ui.viewmodel.searchGroup.SearchTab
import kotlinx.coroutines.launch


@Composable
fun SearchMainScreen(
	viewModel: SearchMainViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	when(uiState) {
		is SearchMainUIState.Loading -> {
			CustomLoadingIndicator()
		}
		is SearchMainUIState.Error -> {
			//TODO
		}
		is SearchMainUIState.Success -> {
			val successUIState = uiState as SearchMainUIState.Success

			val buttonsMetadata = listOf(
				Triple(
					R.string.search_main_screen__button_all,
					R.drawable.icon_star_fill0,
					R.drawable.icon_star_fill1
				),
				Triple(
					R.string.search_main_screen__button_items,
					R.drawable.icon_item_fill0,
					R.drawable.icon_item_fill1
				),
				Triple(
					R.string.search_main_screen__button_places,
					R.drawable.icon_place_fill0,
					R.drawable.icon_place_fill1
				)
			)

			val placeholder: Int = when(successUIState.selectedTab) {
				SearchTab.ALL -> R.string.search_main_screen__all__search_hint
				SearchTab.ITEMS -> R.string.search_main_screen__items__search_hint
				SearchTab.PLACES -> R.string.search_main_screen__places__search_hint
			}

			val chipsMetadata = when(successUIState.selectedTab) {
				SearchTab.ALL -> listOf(
					Triple(
						R.string.common__entity__item__plural,
						R.drawable.icon_item_fill0,
						R.drawable.icon_item_fill1
					),
					Triple(
						R.string.common__entity__site__plural,
						R.drawable.icon_site_fill0,
						R.drawable.icon_site_fill1
					),
					Triple(
						R.string.common__entity__spot__plural,
						R.drawable.icon_spot_fill0,
						R.drawable.icon_spot_fill1
					),
					Triple(
						R.string.common__entity__container__plural,
						R.drawable.icon_container_fill0,
						R.drawable.icon_container_fill1
					)
				)
				SearchTab.ITEMS -> listOf(
					Triple(
						R.string.common__entity__item__plural,
						R.drawable.icon_item_fill0,
						R.drawable.icon_item_fill1
					),
					Triple(
						R.string.item_details__archived,
						R.drawable.icon_archived_fill0,
						R.drawable.icon_archived_fill1
					),
					Triple(
						R.string.item_details__sold,
						R.drawable.icon_sold,
						R.drawable.icon_sold
					)
				)
				SearchTab.PLACES -> listOf(
					Triple(
						R.string.common__entity__site__plural,
						R.drawable.icon_site_fill0,
						R.drawable.icon_site_fill1
					),
					Triple(
						R.string.common__entity__spot__plural,
						R.drawable.icon_spot_fill0,
						R.drawable.icon_spot_fill1
					),
					Triple(
						R.string.common__entity__container__plural,
						R.drawable.icon_container_fill0,
						R.drawable.icon_container_fill1
					)
				)
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						horizontal = dimensionResource(R.dimen.screen_border_padding_hor),
						vertical = dimensionResource(R.dimen.screen_border_padding_ver)
					)
			) {
				SearchSearchBar(
					placeholder = placeholder,
					searchQueryState = successUIState.searchQuery,
					onSearchQueryChange = { viewModel.search(it) },
					modifier = Modifier.fillMaxWidth()
				)

				Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_small)))

				CustomConnectedButtonsGroup(
					buttonsMetadata = buttonsMetadata,
					onCallbackList = listOf(
						{ viewModel.selectTab(SearchTab.ALL) },
						{ viewModel.selectTab(SearchTab.ITEMS) },
						{ viewModel.selectTab(SearchTab.PLACES) }
					)
				)

				CustomFilterChipsGroup(
					chipsMetadata = chipsMetadata,
					onCallbackList = List(chipsMetadata.size) { {} },
				)

				FilterLabelsChipsGroup(
					labels = emptyList(),
					selectedLabelsState = successUIState.selectedLabels,
				)

				SearchResultsList()
			}
		}
	}
}

@Composable
fun SearchSearchBar(
	@StringRes placeholder: Int,
	searchQueryState: String,
	onSearchQueryChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	val focusManager: FocusManager = LocalFocusManager.current
	val focusRequester: FocusRequester = remember { FocusRequester() }
	var selected by rememberSaveable { mutableStateOf(false) }

	Surface(
		color = MaterialTheme.colorScheme.surfaceContainer,
		shape = MaterialTheme.shapes.extraExtraLarge,
		modifier = modifier
			.fillMaxWidth()
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.height(dimensionResource(R.dimen.search_bar_h))
				.clickable {
					selected = true
					focusRequester.requestFocus()
				}
		) {
			if (selected) {
				IconButton(
					onClick = {
						selected = false
						focusManager.clearFocus()
						onSearchQueryChange("")
					},
					modifier = Modifier
						.padding(horizontal = dimensionResource(R.dimen.padding_extra_small))
				) {
					Icon(
						painter = painterResource(R.drawable.icon_backarrow),
						contentDescription = null
					)
				}
			}
			else {
				IconButton(
					onClick = {
						selected = true
						focusRequester.requestFocus()
				  	},
					modifier = Modifier
						.padding(horizontal = dimensionResource(R.dimen.padding_extra_small))
				) {
					Icon(
						painter = painterResource(R.drawable.icon_search),
						contentDescription = null,
					)
				}
			}

			TextField(
				value = searchQueryState,
				onValueChange = { onSearchQueryChange(it) },
				placeholder = { Text(stringResource(placeholder)) },
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Search
				),
				keyboardActions = KeyboardActions(
					onSearch = { focusManager.clearFocus() }
				),
				colors = TextFieldDefaults.colors(
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent,
					errorIndicatorColor = Color.Transparent,
					focusedContainerColor = Color.Transparent,
					unfocusedContainerColor = Color.Transparent,
					disabledContainerColor = Color.Transparent,
					errorContainerColor = Color.Transparent
				),
				modifier = Modifier
					.weight(1f)
					.focusRequester(focusRequester)
					.onFocusEvent({ selected = it.isFocused })
			)

			if (selected) {
				IconButton(
					onClick = { onSearchQueryChange("") },
					modifier = Modifier
						.padding(horizontal = dimensionResource(R.dimen.padding_extra_small))
				) {
					Icon(
						painter = painterResource(R.drawable.icon_close),
						contentDescription = null
					)
				}
			}
			else {
				IconButton(
					onClick = {},
					modifier = Modifier
						.padding(horizontal = dimensionResource(R.dimen.padding_extra_small))
				) {
					Icon(
						painter = painterResource(R.drawable.icon_more),
						contentDescription = null
					)
				}
			}

		}
	}
}

@Composable
fun FilterLabelsChipsGroup(
	labels: List<Label>,
	selectedLabelsState: MutableList<Int>,
	modifier: Modifier = Modifier
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.button_group_gap_hor)),
		modifier = modifier.fillMaxWidth()
	) {
		labels.forEachIndexed { index, label ->
			var selected by rememberSaveable { mutableStateOf(false) }
			FilterChip(
				selected = selected,
				onClick = {
					selected = !selected
					if (selected) {
						selectedLabelsState.add(label.id)
					} else {
						selectedLabelsState.remove(label.id)
					}
			  	},
				label = { Text(label.name) },
				trailingIcon = {
					if (selected) {
						Icon(
							painter = painterResource(R.drawable.icon_label_fill1),
							contentDescription = null,
							tint = label.color
						)
					}
				},
				modifier = Modifier
			)
		}
	}
}

@Composable
fun SearchResultsList() {

}

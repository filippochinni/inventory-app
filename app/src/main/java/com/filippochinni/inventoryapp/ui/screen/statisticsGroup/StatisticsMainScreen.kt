package com.filippochinni.inventoryapp.ui.screen.statisticsGroup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomConnectedButtonsGroup
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomLoadingIndicator
import com.filippochinni.inventoryapp.ui.screen._screenUtils.CustomSectionHeader
import com.filippochinni.inventoryapp.ui.viewmodel.statisticsGroup.StatisticsMainUIState
import com.filippochinni.inventoryapp.ui.viewmodel.statisticsGroup.StatisticsMainViewModel
import com.filippochinni.inventoryapp.ui.viewmodel.statisticsGroup.StatisticsTab


@Composable
fun StatisticsMainScreen(
	viewModel: StatisticsMainViewModel = hiltViewModel()
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	when (uiState) {
		is StatisticsMainUIState.Loading -> {
			CustomLoadingIndicator()
		}
		is StatisticsMainUIState.Error -> {
			//TODO
		}
		is StatisticsMainUIState.Success -> {
			val successUIState = uiState as StatisticsMainUIState.Success

			val buttonsMetadata = listOf(
				Triple(
					R.string.statistics_main_screen__button_items,
					R.drawable.icon_item_fill0,
					R.drawable.icon_item_fill1
				),
				Triple(
					R.string.statistics_main_screen__button_archived,
					R.drawable.icon_archived_fill0,
					R.drawable.icon_archived_fill1
				),
				Triple(
					R.string.statistics_main_screen__button_sold,
					R.drawable.icon_sold,
					R.drawable.icon_sold
				)
			)

			val statsData: List<Triple<Int, Int, String>>
			val headerContent: Int
			when(successUIState.selectedTab) {
				StatisticsTab.ITEMS -> {
					statsData = listOf(
						Triple(R.drawable.icon_globe, R.string.statistics_main_screen__items__total_items, successUIState.itemsStats[0]),
						Triple(R.drawable.icon_item_fill0, R.string.statistics_main_screen__items__unique_items, successUIState.itemsStats[1]),
						Triple(R.drawable.icon_sold, R.string.statistics_main_screen__items__total_value, successUIState.itemsStats[2])
					)
					headerContent = R.string.statistics_main_screen__section_title_stats
				}

				StatisticsTab.ARCHIVED -> {
					statsData = listOf(
						Triple(R.drawable.icon_archived_fill0, R.string.statistics_main_screen__archived__total_archived, successUIState.archivedStats[0]),
						Triple(R.drawable.icon_item_fill0, R.string.statistics_main_screen__archived__unique_archived, successUIState.archivedStats[1]),
						Triple(R.drawable.icon_clock, R.string.statistics_main_screen__archived__archived_over_total, successUIState.archivedStats[2])
					)
					headerContent = R.string.statistics_main_screen__section_title_stats_archived
				}

				StatisticsTab.SOLD -> {
					statsData = listOf(
						Triple(R.drawable.icon_shoppingcart, R.string.statistics_main_screen__sold__total_sold, successUIState.soldStats[0]),
						Triple(R.drawable.icon_shoppingbag, R.string.statistics_main_screen__sold__unique_sold, successUIState.soldStats[1]),
						Triple(R.drawable.icon_sold, R.string.statistics_main_screen__sold__total_profit, successUIState.soldStats[2]),
						Triple(R.drawable.icon_percent, R.string.statistics_main_screen__sold__avg_profit, successUIState.soldStats[3])
					)
					headerContent = R.string.statistics_main_screen__section_title_stats_sold
				}
			}

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						horizontal = dimensionResource(R.dimen.screen_border_padding_hor),
						vertical = dimensionResource(R.dimen.screen_border_padding_ver)
					)
			) {
				CustomConnectedButtonsGroup(
					buttonsMetadata = buttonsMetadata,
					onCallbackList = listOf(
						{ viewModel.selectTab(StatisticsTab.ITEMS) },
						{ viewModel.selectTab(StatisticsTab.ARCHIVED) },
						{ viewModel.selectTab(StatisticsTab.SOLD) }
					),
					modifier = Modifier
				)
				Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))

				CustomSectionHeader(headerContent)
				StatisticsStatsList(statsData, modifier = Modifier)

				Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))

				CustomSectionHeader(R.string.statistics_main_screen__section_title_graph)
				StatisticsGraphSection(modifier = Modifier)
			}
		}
	}
}

@Composable
fun StatisticsStatsList(
	statsData: List<Triple<Int, Int, String>>,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
	) {
		statsData.forEach {
			ListItem(
				leadingContent = {
					Icon(
						painter = painterResource(it.first),
						contentDescription = null,
					)
				},
				trailingContent = {
					Text(
						text = it.third,
						style = MaterialTheme.typography.labelLarge,
						modifier = Modifier
					)
				},
				modifier = Modifier
					.fillMaxWidth()
			) {
				Text(stringResource(it.second))
			}
		}
	}
}

@Composable
fun StatisticsGraphSection(
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.padding(top = dimensionResource(R.dimen.padding_medium))
	) {
		Image(
			painter = painterResource(R.drawable.img_placeholder),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.aspectRatio(16f / 9f)
				.fillMaxWidth()
				.clip(MaterialTheme.shapes.large)
		)
	}
}

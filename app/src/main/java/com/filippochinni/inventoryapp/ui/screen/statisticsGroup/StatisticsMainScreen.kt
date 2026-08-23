package com.filippochinni.inventoryapp.ui.screen.statisticsGroup

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonGroup
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room3.util.TableInfo
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
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						horizontal = dimensionResource(R.dimen.screen_border_padding_hor),
						vertical = dimensionResource(R.dimen.screen_border_padding_ver)
					)
			) {
				val labelItems = listOf(
					R.string.statistics_main_screen__button_items,
					R.string.statistics_main_screen__button_archived,
					R.string.statistics_main_screen__button_sold
				)
				val iconItems0 = listOf(
					R.drawable.icon_item_fill0,
					R.drawable.icon_archived_fill0,
					R.drawable.icon_sold
				)
				val iconItems1 = listOf(
					R.drawable.icon_item_fill1,
					R.drawable.icon_archived_fill1,
					R.drawable.icon_sold
				)
				CustomConnectedButtonsGroup(
					labelsList = labelItems,
					iconNormalList = iconItems0,
					iconSelectedList = iconItems1,
					onCallbackList = listOf(
						{ },
						{ /* Handle archived button click */ },
						{ /* Handle sold button click */ }
					),
					modifier = Modifier
				)
				Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_large)))
				CustomSectionHeader(R.string.statistics_main_screen__section_title_stats)

				val statsData = when(successUIState.selectedTab) {
					StatisticsTab.ITEMS -> listOf(
						Triple(R.drawable.icon_globe, R.string.statistics_main_screen__items__total_items, "100"),
						Triple(R.drawable.icon_item_fill0, R.string.statistics_main_screen__items__unique_items, "50"),
						Triple(R.drawable.icon_sold, R.string.statistics_main_screen__items__total_value, "30")
					)

					StatisticsTab.ARCHIVED -> listOf(
						Triple(R.drawable.icon_globe, R.string.statistics_main_screen__archived__total_archived, "20"),
						Triple(R.drawable.icon_archived_fill0, R.string.statistics_main_screen__archived__unique_archived, "10"),
						Triple(R.drawable.icon_sold, R.string.statistics_main_screen__archived__archived_over_total, "5")
					)

					StatisticsTab.SOLD -> listOf(
						Triple(R.drawable.icon_globe, R.string.statistics_main_screen__sold__total_sold, "15"),
						Triple(R.drawable.icon_sold, R.string.statistics_main_screen__sold__unique_sold, "8"),
						Triple(R.drawable.icon_item_fill0, R.string.statistics_main_screen__sold__total_profit, "3"),
						Triple(R.drawable.icon_item_fill0, R.string.statistics_main_screen__sold__avg_profit, "3")
					)
				}

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
					Text(text = it.third)
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

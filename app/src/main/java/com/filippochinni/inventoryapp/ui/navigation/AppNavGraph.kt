package com.filippochinni.inventoryapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.AppNavBar
import com.filippochinni.inventoryapp.ui.AppTopBar
import com.filippochinni.inventoryapp.ui.screen.InventoryTabScreen
import com.filippochinni.inventoryapp.ui.screen.LabelsTabScreen
import com.filippochinni.inventoryapp.ui.screen.SearchTabScreen
import com.filippochinni.inventoryapp.ui.screen.SettingsTabScreen
import com.filippochinni.inventoryapp.ui.screen.StatisticsTabScreen
import com.filippochinni.inventoryapp.ui.screen.notImplementedToast
import kotlinx.serialization.Serializable

@Serializable
data object InventoryTabRoute : NavKey

@Serializable
data object LabelsTabRoute : NavKey

@Serializable
data object SearchTabRoute : NavKey

@Serializable
data object StatisticsTabRoute : NavKey

@Serializable
data object SettingsTabRoute : NavKey

data class NavBarItem(
    val navKey: NavKey,
    @DrawableRes val icon: Int,
    val description: String
)

val NAV_ITEMS = listOf(
    NavBarItem(InventoryTabRoute, R.drawable.icon_item_fill0, "Inventory"),
    NavBarItem(LabelsTabRoute, R.drawable.icon_label_fill0, "Labels"),
    NavBarItem(SearchTabRoute, R.drawable.icon_search_fill0, "Search"),
    NavBarItem(StatisticsTabRoute, R.drawable.icon_statistics_fill0, "Statistics"),
    NavBarItem(SettingsTabRoute, R.drawable.icon_profile_fill0, "Settings"),
)

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<Any>(InventoryTabRoute) }
    val navigationState = remember { NavigationState(backStack) }
    val navigator = remember { Navigator(navigationState) }
    val context = LocalContext.current  //TODO: remove after notImplementedToast is removed

    val entryProvider = entryProvider<Any> {
        entry<InventoryTabRoute> { InventoryTabScreen({ notImplementedToast(context) }) }
        entry<LabelsTabRoute> { LabelsTabScreen() }
        entry<SearchTabRoute> { SearchTabScreen() }
        entry<StatisticsTabRoute> { StatisticsTabScreen() }
        entry<SettingsTabRoute> { SettingsTabScreen() }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = navigationState.topLevelRoute.toString(),
                canNavigateBack = navigator.canGoBack(),
                navigateAction = { navigator.goBack() },
                modifier = Modifier
            )
        },
        bottomBar = {
            AppNavBar(
                navBarItems = NAV_ITEMS,
                topLevelRoute = navigationState.topLevelRoute,
                onNavItemClick = { navigator.navigate(it) }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { navigator.goBack() },
            entryProvider = entryProvider,
            modifier = modifier.padding(innerPadding)
        )
    }
}

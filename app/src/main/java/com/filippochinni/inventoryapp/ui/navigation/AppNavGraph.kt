package com.filippochinni.inventoryapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.AppNavBar
import com.filippochinni.inventoryapp.ui.AppTopBar
import com.filippochinni.inventoryapp.ui.screen._screenUtils.notImplementedToast
import com.filippochinni.inventoryapp.ui.screen.inventoryGroup.InventoryCreateScreen
import com.filippochinni.inventoryapp.ui.screen.inventoryGroup.InventoryEditScreen
import com.filippochinni.inventoryapp.ui.screen.inventoryGroup.InventoryMainScreen
import com.filippochinni.inventoryapp.ui.screen.labelsGroup.LabelCreateScreen
import com.filippochinni.inventoryapp.ui.screen.labelsGroup.LabelEditScreen
import com.filippochinni.inventoryapp.ui.screen.labelsGroup.LabelsMainScreen
import com.filippochinni.inventoryapp.ui.screen.searchGroup.SearchMainScreen
import com.filippochinni.inventoryapp.ui.screen.settingsGroup.SettingsAboutScreen
import com.filippochinni.inventoryapp.ui.screen.settingsGroup.SettingsMainScreen
import com.filippochinni.inventoryapp.ui.screen.statisticsGroup.StatisticsMainScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data object InventoryMainRoute : NavKey

@Serializable
data object LabelsMainRoute : NavKey

@Serializable
data object SearchMainRoute : NavKey

@Serializable
data object StatisticsMainRoute : NavKey

@Serializable
data object SettingsMainRoute : NavKey

@Serializable
data object InventoryCreateRoute : NavKey

@Serializable
data class InventoryEditRoute(val inventoryId: Int = 0) : NavKey

@Serializable
data object LabelCreateRoute : NavKey

@Serializable
data class LabelEditRoute(val labelId: Int = 0) : NavKey

@Serializable
object SettingsAboutRoute : NavKey

open class NavRoute(
    val navKey: NavKey,
    @StringRes val routeTitle: Int,
    val section: NavKey,
    val helpDestination: String? = null,
)

class NavBarElement(
    navKey: NavKey,
    @StringRes routeTitle: Int,
    section: NavKey,
    helpDestination: String? = null,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
) : NavRoute(navKey, routeTitle, section, helpDestination)



val NAV_BAR_ROUTES: Map<KClass<out NavKey>, NavBarElement> = mapOf(
    InventoryMainRoute::class to NavBarElement(
        InventoryMainRoute,
        R.string.nav_bar__inventory,
        InventoryMainRoute,
        null,
        R.drawable.icon_item_fill0,
        R.drawable.icon_item_fill1
    ),
    LabelsMainRoute::class to NavBarElement(
        LabelsMainRoute,
        R.string.nav_bar__labels,
        LabelsMainRoute,
        null,
        R.drawable.icon_label_fill0,
        R.drawable.icon_label_fill1
    ),
    SearchMainRoute::class to NavBarElement(
        SearchMainRoute,
        R.string.nav_bar__search,
        SearchMainRoute,
        null,
        R.drawable.icon_search,
        R.drawable.icon_search
    ),
    StatisticsMainRoute::class to NavBarElement(
        StatisticsMainRoute,
        R.string.nav_bar__statistics,
        StatisticsMainRoute,
        null,
        R.drawable.icon_statistics,
        R.drawable.icon_statistics
    ),
    SettingsMainRoute::class to NavBarElement(
        SettingsMainRoute,
        R.string.nav_bar__settings,
        SettingsMainRoute,
        null,
        R.drawable.icon_profile_fill0,
        R.drawable.icon_profile_fill1
    ),
)

val NAV_ROUTES: Map<Any, NavRoute> = NAV_BAR_ROUTES + mapOf(
    InventoryCreateRoute::class to NavRoute(
        InventoryCreateRoute,
        R.string.top_bar_nav_title__create_inventory,
        InventoryMainRoute,
        null,
    ),
    InventoryEditRoute::class to NavRoute(
        InventoryEditRoute(),
        R.string.top_bar_nav_title__edit_inventory,
        InventoryMainRoute,
        null,
    ),

    LabelCreateRoute::class to NavRoute(
        LabelCreateRoute,
        R.string.top_bar_nav_title__create_label,
        LabelsMainRoute,
        null,
    ),
    LabelEditRoute::class to NavRoute(
        LabelEditRoute(),
        R.string.top_bar_nav_title__edit_label,
        LabelsMainRoute,
        null,
    ),
    SettingsAboutRoute::class to NavRoute(
        SettingsAboutRoute,
        R.string.top_bar_nav_title__about,
        SettingsMainRoute,
        null,
    ),

)

val HIDE_TOP_BAR_ROUTES = listOf(
    SearchMainRoute::class,
    SettingsMainRoute::class
)

val HIDE_BOTTOM_BAR_ROUTES = emptyList<KClass<out NavKey>>(

)


const val HELP_LINK_BASE = "https://github.com/filippochinni/inventory-app"


@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(InventoryMainRoute)
    val navigationState = remember(backStack) { NavigationState(backStack) }
    val navigator = remember(navigationState) { Navigator(navigationState) }
    val context = LocalContext.current  //TODO: remove after notImplementedToast is removed

    val entryProvider = entryProvider<Any> {
        entry<InventoryMainRoute> { InventoryMainScreen(
            { navigator.navigate(InventoryCreateRoute) },
            { selectedId -> navigator.navigate(SettingsMainRoute) },
            { selectedId -> navigator.navigate(InventoryEditRoute(selectedId)) }
        ) }
        entry<LabelsMainRoute> { LabelsMainScreen(
            { navigator.navigate(LabelCreateRoute) },
            { selectedId -> navigator.navigate(SettingsMainRoute) },
            { selectedId -> navigator.navigate(LabelEditRoute(selectedId)) }
        )
            notImplementedToast(context)    //TODO: remove after LabelsMainScreen is implemented
        }
        entry<SearchMainRoute> { SearchMainScreen()
            notImplementedToast(context)    //TODO: remove after SearchMainScreen is implemented
        }
        entry<StatisticsMainRoute> { StatisticsMainScreen()
            notImplementedToast(context)    //TODO: remove after StatisticsMainScreen is implemented
        }
        entry<SettingsMainRoute> { SettingsMainScreen(
            { navigator.navigate(SettingsAboutRoute) }
        )
            notImplementedToast(context)    //TODO: remove after SettingsMainScreen is implemented
        }

        entry<InventoryCreateRoute> { InventoryCreateScreen(
			onCancelNav = { navigator.goBack() },
			onConfirmNav = { navigator.goBack() },
		) }
        entry<InventoryEditRoute> { arg -> InventoryEditScreen(
            inventoryId = arg.inventoryId,
//			onCancelNav = { navigator.goBack() },
//			onConfirmNav = { navigator.goBack() },
		) }

        entry<LabelCreateRoute> { LabelCreateScreen(
            onCancelNav = { navigator.goBack() },
            onConfirmNav = { navigator.goBack() },
        ) }
        entry<LabelEditRoute> { arg -> LabelEditScreen(
            labelId = arg.labelId,
//			onCancelNav = { navigator.goBack() },
//			onConfirmNav = { navigator.goBack() },
        ) }

        entry<SettingsAboutRoute> { SettingsAboutScreen() }
    }

    fun getTitleFromRoute() = NAV_ROUTES[navigationState.currentRoute::class]?.routeTitle ?: R.string.app_name
    fun getHelpFromRoute() = NAV_ROUTES[navigationState.currentRoute::class]?.helpDestination ?: HELP_LINK_BASE
    fun shouldShowTopBar() = !HIDE_TOP_BAR_ROUTES.contains(navigationState.currentRoute::class)
    fun shouldShowBottomBar() = !HIDE_BOTTOM_BAR_ROUTES.contains(navigationState.currentRoute::class)
    Scaffold(
        topBar = {
            if (shouldShowTopBar()) {
                AppTopBar(
                    title = stringResource(getTitleFromRoute()),
                    helpDestination = getHelpFromRoute(),
                    canNavigateBack = navigator.canGoBack(),
                    navigateAction = { navigator.goBack() },
                    modifier = Modifier
                )
            }
        },
        bottomBar = {
            if (shouldShowBottomBar()) {
                AppNavBar(
                    navBarElements = NAV_BAR_ROUTES.values.toList(),
                    selectedSection = NAV_ROUTES[navigationState.currentRoute::class]?.section ?: InventoryMainRoute,
                    onNavItemClick = { navigator.navigate(it) },
                    modifier = Modifier
                )
            }
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

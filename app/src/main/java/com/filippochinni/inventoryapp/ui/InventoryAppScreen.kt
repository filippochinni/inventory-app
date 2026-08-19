package com.filippochinni.inventoryapp.ui

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation3.runtime.NavKey
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.navigation.AppNavGraph
import com.filippochinni.inventoryapp.ui.navigation.NavBarItem


@Composable
fun InventoryApp() {
	AppNavGraph()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
	title: String,
	canNavigateBack: Boolean,
	navigateAction: () -> Unit,
	modifier: Modifier = Modifier
) {
	CenterAlignedTopAppBar(
		title = { Text(title) },
		navigationIcon = {
			if (canNavigateBack) {
				IconButton(onClick = navigateAction) {
					Icon(
						painter = painterResource(R.drawable.icon_backarrow),
						contentDescription = null
					)
				}
			}
			else {
				IconButton(onClick = {}) {
					Icon(
						painter = painterResource(R.drawable.ic_launcher_foreground),
						contentDescription = null
					)
				}
			}
		},
		modifier = modifier
	)
}

@Composable
fun AppNavBar(
	navBarItems: List<NavBarItem>,
	topLevelRoute: NavKey,
	onNavItemClick: (NavKey) -> Unit,
	modifier: Modifier = Modifier
) {
	NavigationBar(
		modifier = modifier
	) {
		navBarItems.forEach {
			NavigationBarItem(
				selected = it.navKey == topLevelRoute,
				onClick = { onNavItemClick(it.navKey) },
				icon = {
					Icon(
						painter = painterResource(it.icon),
						contentDescription = it.description
					)
				},
				label = {
					Text(it.description)
				},
				modifier = Modifier
			)
		}
	}
}

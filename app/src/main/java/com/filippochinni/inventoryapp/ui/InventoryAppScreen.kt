package com.filippochinni.inventoryapp.ui

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.navigation3.runtime.NavKey
import com.filippochinni.inventoryapp.R
import com.filippochinni.inventoryapp.ui.navigation.AppNavGraph
import com.filippochinni.inventoryapp.ui.navigation.NavBarElement


@Composable
fun InventoryApp() {
	AppNavGraph()
}


@Composable
fun AppTopBar(
	title: String,
	helpDestination: String,
	canNavigateBack: Boolean,
	navigateAction: () -> Unit,
	modifier: Modifier = Modifier
) {
	TopAppBar(
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
						contentDescription = null,
						tint = Color.Unspecified
					)
				}
			}
		},
		actions = { TopAppBarMoreButton(helpDestination) },
		modifier = modifier
	)
}

@Composable
fun AppNavBar(
	navBarElements: List<NavBarElement>,
	selectedSection: NavKey,
	onNavItemClick: (NavKey) -> Unit,
	modifier: Modifier = Modifier
) {
	NavigationBar(
		modifier = modifier
	) {
		navBarElements.forEach {
			NavigationBarItem(
				selected = it.navKey == selectedSection,
				onClick = { onNavItemClick(it.navKey) },
				icon = {
					Icon(
						painter = painterResource(
							if (it.navKey == selectedSection) it.iconSelected
							else it.icon),
						contentDescription = stringResource(it.routeTitle)
					)
				},
				label = {
					Text(stringResource(it.routeTitle))
				},
				modifier = Modifier
			)
		}
	}
}

@Composable
private fun TopAppBarMoreButton(helpDestination: String) {
	val context = LocalContext.current
	var expanded by remember { mutableStateOf(false) }

	Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
		IconButton(onClick = { expanded = true }) {
			Icon(
				painter = painterResource(R.drawable.icon_more),
				contentDescription = null
			)
		}
		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false }
		) {
			DropdownMenuItem(
				text = {
					Text(
						text = stringResource(R.string.top_bar__help),
						style = MaterialTheme.typography.bodyLarge
					)
				},
				onClick = {
					expanded = false
					val intent = Intent(
						Intent.ACTION_VIEW,
						helpDestination.toUri()
					)
					context.startActivity(intent)
				}
			)
		}
	}
}

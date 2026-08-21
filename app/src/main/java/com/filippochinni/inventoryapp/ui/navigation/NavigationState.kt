package com.filippochinni.inventoryapp.ui.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey

class NavigationState(
    val backStack: SnapshotStateList<Any> = mutableStateListOf(InventoryMainRoute),
) {
    val currentRoute: NavKey
        get() = backStack.last() as NavKey
}

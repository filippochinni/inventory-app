package com.filippochinni.inventoryapp.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class NavigationState(
    val backStack: NavBackStack<NavKey>,
) {
    val currentRoute: NavKey
        get() = backStack.last()
}

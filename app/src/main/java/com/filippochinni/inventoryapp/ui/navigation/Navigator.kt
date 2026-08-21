package com.filippochinni.inventoryapp.ui.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state: NavigationState) {
    fun navigate(route: NavKey) {
        state.backStack.add(route)
    }

    fun goBack() {
        if (state.backStack.size > 1) {
            state.backStack.removeAt(state.backStack.lastIndex)
        }
    }

    fun canGoBack(): Boolean {
		return state.backStack.size > 1
	}

}

package com.filippochinni.inventoryapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlin.text.get

class Navigator(val state: NavigationState){
	fun navigate(route: NavKey){
		if (route in state.backStacks.keys){
			state.topLevelRoute = route
		} else {
			state.backStacks[state.topLevelRoute]?.add(route)
		}
	}

	fun goBack(){
		val currentStack = state.backStacks[state.topLevelRoute] ?:
		error("Stack for ${state.topLevelRoute} not found")
		val currentRoute = currentStack.last()

		if (currentRoute == state.topLevelRoute){
			state.topLevelRoute = state.startRoute
		} else {
			currentStack.removeLastOrNull()
		}
	}

	fun canGoBack(): Boolean {
		val currentStack = state.backStacks[state.topLevelRoute] ?: return false
		val isAtTabRoot = currentStack.last() == state.topLevelRoute
		val isStartTab = state.topLevelRoute == state.startRoute

		return !isAtTabRoot || !isStartTab
	}
}

package com.filippochinni.inventoryapp.ui.screen._screenUtils

import android.content.Context
import android.widget.Toast
import com.filippochinni.inventoryapp.model.Inventory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun notImplementedToast(context: Context) {
    Toast.makeText(
		context,
		"Feature Not Implemented Yet",
		Toast.LENGTH_SHORT
	).show()
}

fun mockInventoryList(count: Int) : Flow<List<Inventory>> {
	if (count == 0) {
		return flow { emit(emptyList()) }
	}
	val returnList: List<Inventory> = listOf(
		Inventory(1, "Cacca pazza", "ciao", true),
		Inventory(2, "Item 2", "desc 2", false),
		Inventory(3, "Item 3", "desc 3", false),
		Inventory(4, "Item 3", "desc 3", false),
		Inventory(5, "Item 3", "lorem ipsum cazza pazza ciao bellaldfjfje lorem ipsum cazza pazza ciao bellaldfjfjelorem ipsum cazza pazza ciao bella ldfjfjelorem lorem ipsum cazza pazza ciao bellaldfjfje ipsum cazza pazza ciao bellaldfjfje", false),
		Inventory(6, "Item 3", "desc 3", false),
		Inventory(7, "Item 3", "desc 3", false),
		Inventory(8, "Item 3", "desc 3", false),
		Inventory(9, "Item 3", "desc 3", false),
	).take(count)

	return flow { emit(returnList) }
}

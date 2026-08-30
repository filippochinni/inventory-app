package com.filippochinni.inventoryapp.ui.screen._screenUtils

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import com.filippochinni.inventoryapp.model.Inventory
import com.filippochinni.inventoryapp.model.Label
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
		Inventory(1, "Test Test", "ciao", true),
		Inventory(2, "Item 2", "desc 2", false),
		Inventory(3, "Item 3", "desc 3", false),
		Inventory(4, "Item 4", "desc 4", false),
		Inventory(5, "Item 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", false),
		Inventory(6, "Item 6", "desc 6", false),
		Inventory(7, "abcdefghijklmnopqrst", "", false),
		Inventory(8, "Item 8", "desc 8", false),
		Inventory(9, "Item 9", "desc 9", false),
	).take(count)

	return flow { emit(returnList) }
}

fun mockLabelList(count: Int) : Flow<List<Label>> {
	if (count == 0) {
		return flow { emit(emptyList()) }
	}
	val returnList: List<Label> = listOf(
		Label(1, "Label 1", Color(0xFF6200EE)),
		Label(2, "Label 2", Color(0xFF03DAC5)),
		Label(3, "Label 3", Color(0xFFFF5722)),
		Label(4, "Label 4", Color(0xFF4CAF50)),
		Label(5, "Label 5", Color(0xFFFFC107)),
		Label(6, "Label 6", Color(0xFF9C27B0)),
		Label(7, "Label 7", Color(0xFFE91E63)),
		Label(8, "Label 8", Color(0xFFFF9800)),
		Label(9, "Label 9", Color(0xFF607D8B)),
		Label(10, "Label 10", Color(0xFF795548)),
		Label(11, "Label 11", Color(0xFF2196F3)),
		Label(12, "Label 12", Color(0xFF00BCD4)),
		Label(13, "Label 13", Color(0xFF8BC34A)),
		Label(14, "Label 14", Color(0xFFFFEB3B)),
		Label(15, "Label 15", Color(0xFFCDDC39)),
		Label(16, "Label 16", Color(0xFF673AB7)),
		Label(17, "Label 17", Color(0xFF009688)),
	).take(count)

	return flow { emit(returnList) }
}

fun mockStatisticsList(statType: Int) : Flow<List<String>> {
	val returnList: List<String> = when(statType) {
		0 -> listOf("156", "112", "130$")
		1 -> listOf("43", "35", "17%")
		2 -> listOf("23", "12", "345$", "15.01$")
		else -> emptyList()
	}
	return flow { emit(returnList) }
}

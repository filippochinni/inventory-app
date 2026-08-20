package com.filippochinni.inventoryapp.model

import androidx.compose.ui.graphics.Color


data class Label(
	val id: Int,
	val name: String,
	val color: Color
) : AbstractType

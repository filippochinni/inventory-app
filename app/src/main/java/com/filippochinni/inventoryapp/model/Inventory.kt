package com.filippochinni.inventoryapp.model


data class Inventory(
	val id: Int,
	val name: String,
	val description: String,
	val isActive: Boolean
) : AbstractType

package com.filippochinni.inventoryapp.model


data class Spot(
	val id: Int,
	val name: String,
	val description: String,
	val picture: String?,
	val parentId: Int,
) : AbstractType

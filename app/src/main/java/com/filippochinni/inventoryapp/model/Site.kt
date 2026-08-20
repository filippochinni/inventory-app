package com.filippochinni.inventoryapp.model


data class Site(
	val id: Int = 0,
	val name: String,
	val description: String,
	val address: String,
	val picture: String?,
	val parentId: Int,
) : AbstractType

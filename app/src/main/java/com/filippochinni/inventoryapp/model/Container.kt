package com.filippochinni.inventoryapp.model


data class Container(
	val id: Int,
	val name: String,
	val description: String,
	val dimensionL: Int,
	val dimensionW: Int,
	val dimensionH: Int,
	val picture: String?,
	val templateId: Int?,
	val parentId: Int?
) : AbstractType

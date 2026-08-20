package com.filippochinni.inventoryapp.model

data class Item(
	val id: Int,
	val name: String,
	val description: String,
	val quantity: Int,
	val dimensionL: Int,
	val dimensionW: Int,
	val dimensionH: Int,
	val value: Int,
	val acquisitionDate: Long?,
	val insertDate: Long,
	val isArchived: Boolean,
	val isSold: Boolean,
	val parentId: Int,
	val pictures: List<String>,
	val labels: List<Label>
) : AbstractType

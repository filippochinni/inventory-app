package com.filippochinni.inventoryapp.data.datasource.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "inventory")
data class InventoryEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String,
) : AbstractEntity() {
}

package com.filippochinni.inventoryapp.data.datasource.database.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.filippochinni.inventoryapp.data.datasource.database.AbstractEntity


@Entity(tableName = "inventory")
data class InventoryEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String,
	val description: String,
	val isActive: Boolean
) : AbstractEntity

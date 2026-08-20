package com.filippochinni.inventoryapp.data.datasource.database.entity

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.filippochinni.inventoryapp.data.datasource.database.AbstractEntity


@Entity(tableName = "container", indices = [Index("parentId")])
data class ContainerEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String,
	val description: String,
	val dimensionL: Int,
	val dimensionW: Int,
	val dimensionH: Int,
	val picture: String?,
	val templateId: Int?,
	val parentId: Int?
) : AbstractEntity

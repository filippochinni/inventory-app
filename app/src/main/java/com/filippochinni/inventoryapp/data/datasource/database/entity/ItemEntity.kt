package com.filippochinni.inventoryapp.data.datasource.database.entity

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.filippochinni.inventoryapp.data.datasource.database.AbstractEntity

@Entity(tableName = "item", indices = [Index("parentId")])
data class ItemEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
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
	val picture: String?,
	val parentId: Int
) : AbstractEntity {
	@Entity(tableName = "item_picture", indices = [Index("itemId")])
	data class ItemPictureEntity(
		@PrimaryKey(autoGenerate = true)
		val id: Int = 0,
		val picture: String,
		val itemId: Int
	) : AbstractEntity

	@Entity(tableName = "item_label_association")
	data class ItemLabelAssociation(
		@PrimaryKey val itemId: Int,
		@PrimaryKey val labelId: Int
	) : AbstractEntity
}

package com.filippochinni.inventoryapp.data.datasource.database.entity

import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.filippochinni.inventoryapp.data.datasource.database.AbstractEntity


@Entity(tableName = "site", indices = [Index("parentId")])
data class SiteEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val name: String,
	val description: String,
	val address: String,
	val picture: String?,
	val parentId: Int,
) : AbstractEntity

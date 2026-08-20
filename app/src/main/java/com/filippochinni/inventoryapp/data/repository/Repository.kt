package com.filippochinni.inventoryapp.data.repository

import com.filippochinni.inventoryapp.data.datasource.DTO
import com.filippochinni.inventoryapp.data.datasource.database.AbstractEntity
import com.filippochinni.inventoryapp.model.AbstractType


sealed interface Repository<T : AbstractType, E: DTO> {
	fun mapToModel(dataObject: E): T
	fun mapToDataObject(model: T): E
}

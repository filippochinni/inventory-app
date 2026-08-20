package com.filippochinni.inventoryapp.data.repository.inventoryRepository

import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.InventoryEntity
import com.filippochinni.inventoryapp.data.repository.DBRepository
import com.filippochinni.inventoryapp.data.repository.Local
import com.filippochinni.inventoryapp.model.Inventory
import javax.inject.Inject


@Local
class LocalInventoryRepository @Inject constructor(
	override val dao: DAO<InventoryEntity>
) : InventoryRepository, DBRepository<Inventory, InventoryEntity> {



	override fun mapToModel(dataObject: InventoryEntity): Inventory {
		return Inventory(
			id = dataObject.id,
			name = dataObject.name,
			description = dataObject.description,
			isActive = dataObject.isActive,
		)
	}

	override fun mapToDataObject(model: Inventory): InventoryEntity {
		return InventoryEntity(
			id = model.id,
			name = model.name,
			description = model.description,
			isActive = model.isActive,
		)
	}
}

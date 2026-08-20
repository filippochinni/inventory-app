package com.filippochinni.inventoryapp.data.repository.itemRepository

import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.ItemEntity
import com.filippochinni.inventoryapp.data.repository.DBRepository
import com.filippochinni.inventoryapp.data.repository.Local
import com.filippochinni.inventoryapp.model.Item
import javax.inject.Inject


@Local
class LocalItemRepository @Inject constructor(
	override val dao: DAO<ItemEntity>,
) : ItemRepository, DBRepository<Item, ItemEntity> {



	fun itemEntityToModel(dataObject: Triple<ItemEntity, List<ItemEntity.ItemPictureEntity>, List<ItemEntity.ItemLabelAssociation>>): Item {
		val (itemEntity, pictures, labels) = dataObject
		return mapToModel(itemEntity).copy(
			pictures = pictures.map { it.picture },
			labels = labels.map { it.labelId }
		)
	}

	fun itemModelToEntity(model: Item): Triple<ItemEntity, List<ItemEntity.ItemPictureEntity>, List<ItemEntity.ItemLabelAssociation>> {
		return Triple(
			mapToDataObject(model),
			model.pictures.map { ItemEntity.ItemPictureEntity(picture = it, itemId = model.id) },
			model.labels.map { ItemEntity.ItemLabelAssociation(itemId = model.id, labelId = it) }
		)
	}

	override fun mapToModel(dataObject: ItemEntity): Item {
		return Item(
			id = dataObject.id,
			name = dataObject.name,
			description = dataObject.description,
			quantity = dataObject.quantity,
			dimensionL = dataObject.dimensionL,
			dimensionW = dataObject.dimensionW,
			dimensionH = dataObject.dimensionH,
			value = dataObject.value,
			acquisitionDate = dataObject.acquisitionDate,
			insertDate = dataObject.insertDate,
			isArchived = dataObject.isArchived,
			isSold = dataObject.isSold,
			parentId = dataObject.parentId,
			pictures = emptyList(),
			labels = emptyList()
		)
	}

	override fun mapToDataObject(model: Item): ItemEntity {
		return ItemEntity(
			id = model.id,
			name = model.name,
			description = model.description,
			quantity = model.quantity,
			dimensionL = model.dimensionL,
			dimensionW = model.dimensionW,
			dimensionH = model.dimensionH,
			value = model.value,
			acquisitionDate = model.acquisitionDate,
			insertDate = model.insertDate,
			isArchived = model.isArchived,
			isSold = model.isSold,
			parentId = model.parentId
		)
	}
}

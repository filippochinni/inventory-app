package com.filippochinni.inventoryapp.data.repository.spotRepository

import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.SpotEntity
import com.filippochinni.inventoryapp.data.repository.DBRepository
import com.filippochinni.inventoryapp.data.repository.Local
import com.filippochinni.inventoryapp.model.Spot
import javax.inject.Inject


@Local
class LocalSpotRepository @Inject constructor(
	override val dao: DAO<SpotEntity>
) : SpotRepository, DBRepository<Spot, SpotEntity> {



	override fun mapToModel(dataObject: SpotEntity): Spot {
		return Spot(
			id = dataObject.id,
			name = dataObject.name,
			description = dataObject.description,
			picture = dataObject.picture,
			parentId = dataObject.parentId
		)
	}

	override fun mapToDataObject(model: Spot): SpotEntity {
		return SpotEntity(
			id = model.id,
			name = model.name,
			description = model.description,
			picture = model.picture,
			parentId = model.parentId
		)
	}
}

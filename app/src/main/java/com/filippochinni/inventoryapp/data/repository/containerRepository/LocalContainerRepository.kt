package com.filippochinni.inventoryapp.data.repository.containerRepository

import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.ContainerEntity
import com.filippochinni.inventoryapp.data.repository.DBRepository
import com.filippochinni.inventoryapp.data.repository.Local
import com.filippochinni.inventoryapp.model.Container
import javax.inject.Inject


@Local
class LocalContainerRepository @Inject constructor(
	override val dao: DAO<ContainerEntity>
) : ContainerRepository, DBRepository<Container, ContainerEntity> {



	override fun mapToModel(dataObject: ContainerEntity): Container {
		return Container(
			id = dataObject.id,
			name = dataObject.name,
			description = dataObject.description,
			dimensionL = dataObject.dimensionL,
			dimensionW = dataObject.dimensionW,
			dimensionH = dataObject.dimensionH,
			picture = dataObject.picture,
			templateId = dataObject.templateId,
			parentId = dataObject.parentId
		)
	}

	override fun mapToDataObject(model: Container): ContainerEntity {
		return ContainerEntity(
			id = model.id,
			name = model.name,
			description = model.description,
			dimensionL = model.dimensionL,
			dimensionW = model.dimensionW,
			dimensionH = model.dimensionH,
			picture = model.picture,
			templateId = model.templateId,
			parentId = model.parentId
		)
	}
}

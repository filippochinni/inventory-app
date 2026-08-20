package com.filippochinni.inventoryapp.data.repository.siteRepository

import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.SiteEntity
import com.filippochinni.inventoryapp.data.repository.DBRepository
import com.filippochinni.inventoryapp.data.repository.Local
import com.filippochinni.inventoryapp.model.Site
import javax.inject.Inject


@Local
class LocalSiteRepository @Inject constructor(
	override val dao: DAO<SiteEntity>
) : SiteRepository, DBRepository<Site, SiteEntity> {



	override fun mapToModel(dataObject: SiteEntity): Site {
		return Site(
			id = dataObject.id,
			name = dataObject.name,
			description = dataObject.description,
			address = dataObject.address,
			picture = dataObject.picture,
			parentId = dataObject.parentId
		)
	}

	override fun mapToDataObject(model: Site): SiteEntity {
		return SiteEntity(
			id = model.id,
			name = model.name,
			description = model.description,
			address = model.address,
			picture = model.picture,
			parentId = model.parentId
		)
	}
}

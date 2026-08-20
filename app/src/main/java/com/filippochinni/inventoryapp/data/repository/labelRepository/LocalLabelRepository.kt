package com.filippochinni.inventoryapp.data.repository.labelRepository

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.LabelEntity
import com.filippochinni.inventoryapp.data.repository.DBRepository
import com.filippochinni.inventoryapp.data.repository.Local
import com.filippochinni.inventoryapp.model.Label
import javax.inject.Inject


@Local
class LocalLabelRepository @Inject constructor(
	override val dao: DAO<LabelEntity>
) : LabelRepository, DBRepository<Label, LabelEntity> {



	override fun mapToDataObject(model: Label): LabelEntity {
		return LabelEntity(
			id = model.id,
			name = model.name,
			color = model.color.toArgb()
		)
	}

	override fun mapToModel(dataObject: LabelEntity): Label {
		return Label(
			id = dataObject.id,
			name = dataObject.name,
			color = Color(dataObject.color)
		)
	}
}

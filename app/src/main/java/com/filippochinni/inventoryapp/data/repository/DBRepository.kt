package com.filippochinni.inventoryapp.data.repository

import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.AbstractEntity
import com.filippochinni.inventoryapp.model.AbstractType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface DBRepository<T : AbstractType, E: AbstractEntity> : Repository<T, E> {
	val dao: DAO<E>


	fun selectAll(): Flow<List<T>> {
		return dao.selectAll().map { list ->
			list.map { mapToModel(it) }
		}
	}

	fun selectById(id: Int): Flow<T?> {
		return dao.selectById(id).map { entity ->
			mapToModel(entity)
		}
	}

	suspend fun insert(item: T) {
		dao.insert(mapToDataObject(item))
	}

	suspend fun update(item: T) {
		dao.update(mapToDataObject(item))
	}

	suspend fun delete(item: T) {
		dao.delete(mapToDataObject(item))
	}

}

package com.filippochinni.inventoryapp.data.datasource.database

import kotlinx.coroutines.flow.Flow


interface DAO<T> {
	fun selectAll(): Flow<List<T>>
	fun selectById(id: Int): Flow<T>
	suspend fun insert(entity: T)
	suspend fun update(entity: T)
	suspend fun delete(entity: T)
}

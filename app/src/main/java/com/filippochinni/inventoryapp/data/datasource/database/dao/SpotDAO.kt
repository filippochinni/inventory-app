package com.filippochinni.inventoryapp.data.datasource.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.SpotEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SpotDAO : DAO<SpotEntity> {

	@Query("SELECT * FROM spots")
	override fun selectAll(): Flow<List<SpotEntity>>

	@Query("SELECT * FROM spots WHERE id = :id")
	override fun selectById(id: Int): Flow<SpotEntity>

	@Insert
	override suspend fun insert(entity: SpotEntity)

	@Update
	override suspend fun update(entity: SpotEntity)

	@Delete
	override suspend fun delete(entity: SpotEntity)
}

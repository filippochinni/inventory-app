package com.filippochinni.inventoryapp.data.datasource.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.LabelEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LabelDAO : DAO<LabelEntity> {

	@Query("SELECT * FROM labels")
	override fun selectAll(): Flow<List<LabelEntity>>

	@Query("SELECT * FROM labels WHERE id = :id")
	override fun selectById(id: Int): Flow<LabelEntity>

	@Insert
	override suspend fun insert(entity: LabelEntity)

	@Update
	override suspend fun update(entity: LabelEntity)

	@Delete
	override suspend fun delete(entity: LabelEntity)
}

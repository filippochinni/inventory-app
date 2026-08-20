package com.filippochinni.inventoryapp.data.datasource.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.ContainerEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ContainerDAO : DAO<ContainerEntity> {

	@Query("SELECT * FROM containers")
	override fun selectAll(): Flow<List<ContainerEntity>>

	@Query("SELECT * FROM containers WHERE id = :id")
	override fun selectById(id: Int): Flow<ContainerEntity>

	@Insert
	override suspend fun insert(entity: ContainerEntity)

	@Update
	override suspend fun update(entity: ContainerEntity)

	@Delete
	override suspend fun delete(entity: ContainerEntity)
}

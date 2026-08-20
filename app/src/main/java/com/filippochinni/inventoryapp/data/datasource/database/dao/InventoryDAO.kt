package com.filippochinni.inventoryapp.data.datasource.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.InventoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface InventoryDAO : DAO<InventoryEntity> {

	@Query("SELECT * FROM inventory")
	override fun selectAll(): Flow<List<InventoryEntity>>

	@Query("SELECT * FROM inventory WHERE id = :id")
	override fun selectById(id: Int): Flow<InventoryEntity>

	@Insert
	override suspend fun insert(entity: InventoryEntity)

	@Update
	override suspend fun update(entity: InventoryEntity)

	@Delete
	override suspend fun delete(entity: InventoryEntity)
}

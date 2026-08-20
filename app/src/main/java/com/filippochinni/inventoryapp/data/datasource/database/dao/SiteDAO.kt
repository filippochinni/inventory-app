package com.filippochinni.inventoryapp.data.datasource.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.SiteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SiteDAO : DAO<SiteEntity> {
	@Query("SELECT * FROM sites")
	override fun selectAll(): Flow<List<SiteEntity>>

	@Query("SELECT * FROM sites WHERE id = :id")
	override fun selectById(id: Int): Flow<SiteEntity>

	@Insert
	override suspend fun insert(entity: SiteEntity)

	@Update
	override suspend fun update(entity: SiteEntity)

	@Delete
	override suspend fun delete(entity: SiteEntity)
}

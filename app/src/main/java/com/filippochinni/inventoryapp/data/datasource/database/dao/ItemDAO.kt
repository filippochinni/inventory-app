package com.filippochinni.inventoryapp.data.datasource.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.filippochinni.inventoryapp.data.datasource.database.DAO
import com.filippochinni.inventoryapp.data.datasource.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDAO : DAO<ItemEntity> {

	@Query("SELECT * FROM items")
	override fun selectAll(): Flow<List<ItemEntity>>

	@Query("SELECT * FROM items WHERE id = :id")
	override fun selectById(id: Int): Flow<ItemEntity>

	@Insert
	override suspend fun insert(entity: ItemEntity)

	@Update
	override suspend fun update(entity: ItemEntity)

	@Delete
	override suspend fun delete(entity: ItemEntity)


	@Dao
	interface ItemPictureDAO : DAO<ItemEntity.ItemPictureEntity> {

		@Query("SELECT * FROM item_pictures WHERE item_id = :itemId")
		fun getPicturesByItemId(itemId: Int): Flow<List<ItemEntity.ItemPictureEntity>>

		@Insert
		override suspend fun insert(entity: ItemEntity.ItemPictureEntity)

		@Delete
		override suspend fun delete(entity: ItemEntity.ItemPictureEntity)
	}

	@Dao
	interface ItemLabelAssociationDAO : DAO<ItemEntity.ItemLabelAssociation> {

		@Query("SELECT * FROM item_label_association WHERE item_id = :itemId")
		fun getLabelsByItemId(itemId: Int): Flow<List<ItemEntity.ItemLabelAssociation>>

		@Insert
		override suspend fun insert(entity: ItemEntity.ItemLabelAssociation)

		@Delete
		override suspend fun delete(entity: ItemEntity.ItemLabelAssociation)
	}
}


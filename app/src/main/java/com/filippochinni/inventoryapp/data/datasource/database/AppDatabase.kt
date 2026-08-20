package com.filippochinni.inventoryapp.data.datasource.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.filippochinni.inventoryapp.data.datasource.database.entity.InventoryEntity

@Database(entities = [InventoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

	companion object {
		@Volatile
		private var Instance: AppDatabase? = null

		fun getDatabase(context: Context): AppDatabase {
			return Instance ?: synchronized(this) {
				Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
					.fallbackToDestructiveMigration()
					.build()
					.also { Instance = it }
			}
		}
	}
}

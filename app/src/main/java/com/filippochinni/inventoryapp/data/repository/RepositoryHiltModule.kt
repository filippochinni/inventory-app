package com.filippochinni.inventoryapp.data.repository

import com.filippochinni.inventoryapp.data.repository.containerRepository.ContainerRepository
import com.filippochinni.inventoryapp.data.repository.containerRepository.LocalContainerRepository
import com.filippochinni.inventoryapp.data.repository.inventoryRepository.InventoryRepository
import com.filippochinni.inventoryapp.data.repository.inventoryRepository.LocalInventoryRepository
import com.filippochinni.inventoryapp.data.repository.itemRepository.ItemRepository
import com.filippochinni.inventoryapp.data.repository.itemRepository.LocalItemRepository
import com.filippochinni.inventoryapp.data.repository.labelRepository.LabelRepository
import com.filippochinni.inventoryapp.data.repository.labelRepository.LocalLabelRepository
import com.filippochinni.inventoryapp.data.repository.siteRepository.LocalSiteRepository
import com.filippochinni.inventoryapp.data.repository.siteRepository.SiteRepository
import com.filippochinni.inventoryapp.data.repository.spotRepository.LocalSpotRepository
import com.filippochinni.inventoryapp.data.repository.spotRepository.SpotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Remote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataStore

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryHiltModule {

	@Local
	@Binds
	@Singleton
	abstract fun bindInventoryRepository(
		impl: LocalInventoryRepository
	): InventoryRepository

	@Local
	@Binds
	@Singleton
	abstract fun bindSiteRepository(
		impl: LocalSiteRepository
	): SiteRepository

	@Local
	@Binds
	@Singleton
	abstract fun bindSpotRepository(
		impl: LocalSpotRepository
	): SpotRepository

	@Local
	@Binds
	@Singleton
	abstract fun bindContainerRepository(
		impl: LocalContainerRepository
	): ContainerRepository

	@Local
	@Binds
	@Singleton
	abstract fun bindItemRepository(
		impl: LocalItemRepository
	): ItemRepository

	@Local
	@Binds
	@Singleton
	abstract fun bindLabelRepository(
		impl: LocalLabelRepository
	): LabelRepository
}

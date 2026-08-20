package com.filippochinni.inventoryapp.data.repository

import com.filippochinni.inventoryapp.data.datasource.datastore.datastoreDTO.AbstractDataStoreDTO
import com.filippochinni.inventoryapp.model.AbstractType


interface DataStoreRepository : Repository<AbstractType, AbstractDataStoreDTO> {
}

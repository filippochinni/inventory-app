package com.filippochinni.inventoryapp.data.repository

import com.filippochinni.inventoryapp.data.datasource.network.networkDTO.AbstractNetworkDTO
import com.filippochinni.inventoryapp.model.AbstractType


interface NetworkRepository : Repository<AbstractType, AbstractNetworkDTO> {
}

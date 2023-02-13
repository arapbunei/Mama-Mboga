package com.example.mamamboga.data.repo

import com.example.mamamboga.data.model.OrderGrocery
import kotlinx.coroutines.flow.Flow

interface OrderGroceriesRepository {

    suspend fun getAllOrderGroceries(): Flow<List<OrderGrocery>>

    suspend fun getAddedOrderGrocery(): Flow<List<OrderGrocery>>

    suspend fun add(groceryId: Long): Flow<Boolean>

    suspend fun remove(groceryId: Long): Flow<Boolean>

    suspend fun clear()
}
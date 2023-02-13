package com.example.mamamboga.data.repo

import com.example.mamamboga.data.DataProvider
import com.example.mamamboga.data.model.OrderGrocery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

object RuntimeOrderGroceriesRepository : OrderGroceriesRepository {
    private const val MIN_COFFEE_DRINK_VALUE = 0
    private const val MAX_COFFEE_DRINK_VALUE = 99
    private const val DEFAULT_COFFEE_DRINK_COUNT = 0
    private const val INVALID_INDEX = -1

    private val orderGroceries = mutableListOf<OrderGrocery>()

    override suspend fun getAllOrderGroceries(): Flow<List<OrderGrocery>> {
        if (orderGroceries.isEmpty()) {
            orderGroceries.addAll(DataProvider.getAllBasketGroceries())
        }
        return flowOf(orderGroceries)
    }

    override suspend fun getAddedOrderGrocery(): Flow<List<OrderGrocery>> {
        return getAllOrderGroceries()
            .map { orderGroceries ->
                orderGroceries.filter { orderGrocery ->
                    orderGrocery.count != DEFAULT_COFFEE_DRINK_COUNT
                }
            }
    }

    override suspend fun add(groceryId: Long): Flow<Boolean> {
        val index = orderGroceries.indexOfFirst { it.grocery.id == groceryId }
        val result = if (index != INVALID_INDEX) {
            val orderGrocery = orderGroceries[index]
            val newCountValue = if (orderGrocery.count == MAX_COFFEE_DRINK_VALUE)
                MAX_COFFEE_DRINK_VALUE
            else
                orderGrocery.count + 1

            orderGroceries[index] = orderGrocery.copy(grocery = orderGrocery.grocery, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun remove(groceryId: Long): Flow<Boolean> {
        val index = orderGroceries.indexOfFirst { it.grocery.id == groceryId }
        val result = if (index != INVALID_INDEX) {
            val orderGrocery = orderGroceries[index]
            val newCountValue = if (orderGrocery.count == MIN_COFFEE_DRINK_VALUE)
                MIN_COFFEE_DRINK_VALUE
            else
                orderGrocery.count - 1

            orderGroceries[index] = orderGrocery.copy(grocery = orderGrocery.grocery, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    override suspend fun clear() {
        orderGroceries.clear()
    }
}
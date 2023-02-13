package com.example.mamamboga.ui.screen.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mamamboga.data.repo.OrderGroceriesRepository
import com.example.mamamboga.data.repo.RuntimeOrderGroceriesRepository
import com.example.mamamboga.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal

class BasketViewModel(
    private val repository: OrderGroceriesRepository = RuntimeOrderGroceriesRepository
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<BasketState>> = MutableLiveData()
    val uiState: LiveData<UiState<BasketState>>
        get() = _uiState

    fun loadGroceries() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderGrocery()
                .collect { orderGroceries ->
                    val totalPrice: BigDecimal = orderGroceries.sumOf { it.grocery.price.multiply(BigDecimal(it.count)) }
                    _uiState.value = UiState.Success(BasketState(orderGroceries, totalPrice))
                }
        }
    }

    fun addGrocery(groceryId: Long) {
        viewModelScope.launch {
            repository.add(groceryId)
                .collect { isAdded ->
                    if (isAdded) {
                        loadGroceries()
                    }
                }
        }
    }

    fun removeGrocery(groceryId: Long) {
        viewModelScope.launch {
            repository.remove(groceryId)
                .collect { isRemoved ->
                    if (isRemoved) {
                        loadGroceries()
                    }
                }
        }
    }
}
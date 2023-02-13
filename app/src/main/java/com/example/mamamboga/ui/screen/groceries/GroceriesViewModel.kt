package com.example.mamamboga.ui.screen.Groceries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mamamboga.data.model.OrderGrocery
import com.example.mamamboga.data.repo.OrderGroceriesRepository
import com.example.mamamboga.data.repo.RuntimeOrderGroceriesRepository
import com.example.mamamboga.ui.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GroceriesViewModel(
    private val repository: OrderGroceriesRepository = RuntimeOrderGroceriesRepository
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<List<OrderGrocery>>> = MutableLiveData()
    val uiState: LiveData<UiState<List<OrderGrocery>>>
        get() = _uiState

    fun loadGroceries() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllOrderGroceries()
                .collect { orderGroceries ->
                    _uiState.value = UiState.Success(orderGroceries)
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
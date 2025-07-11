package com.example.meddeliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meddeliveryapp.model.Order
import com.example.meddeliveryapp.model.FirebaseRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val firebaseRepository = FirebaseRepository()

    private val _orderDetails = MutableLiveData<Order>()
    val orderDetails: LiveData<Order> = _orderDetails

    fun getOrderDetails(orderId: String) {
        viewModelScope.launch {
            val order = firebaseRepository.fetchOrderById(orderId)
            _orderDetails.postValue(order)
        }
    }

    // ✅ Add this for use in OrderDisplay screen
    suspend fun fetchAllOrders(): List<Order> {
        return firebaseRepository.fetchAllOrders()
    }
}

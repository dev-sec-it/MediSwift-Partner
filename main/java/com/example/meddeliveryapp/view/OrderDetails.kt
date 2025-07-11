package com.example.meddeliveryapp.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.meddeliveryapp.model.Order
import com.example.meddeliveryapp.viewmodel.HomeViewModel

@Composable
fun OrderDetails(navController: NavController,
                 viewModel: HomeViewModel = viewModel()){
    val orders = remember { mutableStateListOf<Order>() }

    LaunchedEffect(Unit) {
        val fetchedOrders = viewModel.fetchAllOrders() // Add this method in ViewModel
        orders.clear()
        orders.addAll(fetchedOrders)
    }




}
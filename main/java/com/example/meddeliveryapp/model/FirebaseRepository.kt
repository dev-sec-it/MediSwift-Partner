package com.example.meddeliveryapp.model

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val database = FirebaseDatabase.getInstance()
    private val ordersRef = database.getReference("orders")

    // ✅ 1. Fetch all orders and attach their Firebase keys as orderId
    suspend fun fetchAllOrders(): List<Order> {
        val snapshot = ordersRef.get().await()
        val ordersList = mutableListOf<Order>()

        for (orderSnapshot in snapshot.children) {
            val order = orderSnapshot.getValue(Order::class.java)
            val id = orderSnapshot.key ?: continue
            order?.copy(orderId = id)?.let { ordersList.add(it) }
        }

        return ordersList
    }

    // ✅ 2. Fetch a single order by its ID
    suspend fun fetchOrderById(orderId: String): Order? {
        val snapshot = ordersRef.child(orderId).get().await()
        return snapshot.getValue(Order::class.java)?.copy(orderId = orderId)
    }
}

package com.example.meddeliveryapp.model

data class Medicine(
    val name: String = "",
    val qty: Int = 0
)

data class Order(
    val orderId: String = "",
    val status: String = "", // "new", "accepted", "delivered"
    val assignedTo: String = "",
    val pickupLocation: String = "",
    val dropLocation: String = "",
    val customerName: String = "",
    val customerPhone: String = "",
    val medicines: List<Medicine> = listOf(),
    val timestamp: String = ""
)
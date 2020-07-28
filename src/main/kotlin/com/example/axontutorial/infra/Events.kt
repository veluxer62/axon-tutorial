package com.example.axontutorial.infra

data class OrderCreatedEvent(
    val id: String,
    val quantity: Int
)

data class OrderPaidEvent(
    val id: String
)

data class OrderCanceledEvent(
    val id: String
)

data class ItemCreatedEvent(
    val id: String,
    val name: String,
    val stock: Int
)

data class ItemStockChangedEvent(
    val id: String,
    val stock: Int
)
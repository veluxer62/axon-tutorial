package com.example.axontutorial.infra

import com.example.axontutorial.domain.OrderState

data class OrderDto(
    val id: String,
    val state: OrderState,
    val quantity: Int
)

data class CreateOrderDto(
    val id: String,
    val quantity: Int
)
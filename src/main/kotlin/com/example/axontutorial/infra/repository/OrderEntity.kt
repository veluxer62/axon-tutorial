package com.example.axontutorial.infra.repository

import com.example.axontutorial.domain.OrderState
import javax.persistence.*

@Entity
@Table(name = "order_table")
data class OrderEntity(
    @Id
    val id: String,
    @Enumerated(EnumType.STRING)
    var state: OrderState,
    val quantity: Int
) {
    fun pay() {
        state = OrderState.PAID
    }

    fun cancel() {
        state = OrderState.CANCELED
    }
}
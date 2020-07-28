package com.example.axontutorial.infra

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateOrderCommand(
    @TargetAggregateIdentifier
    val id: String,
    val quantity: Int
)

data class ApprovePaymentOrderCommand(
    @TargetAggregateIdentifier
    val id: String
)

data class CancelOrderCommand(
    @TargetAggregateIdentifier
    val id: String
)
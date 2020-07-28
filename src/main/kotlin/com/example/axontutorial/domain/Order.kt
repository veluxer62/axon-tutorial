package com.example.axontutorial.domain

import com.example.axontutorial.infra.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

@Aggregate
class Order() {
    companion object {
        private val logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }

    @AggregateIdentifier
    private lateinit var id: String
    private lateinit var item: Item
    private lateinit var quantity: Number
    private lateinit var state: OrderState

    @CommandHandler
    constructor(command: CreateOrderCommand) : this() {
        logger.debug("command handle $command")

        val event = OrderCreatedEvent(
            id = command.id,
            quantity = command.quantity
        )
        apply(event)
        // TODO Item 매핑 이벤트 발행
    }

    @EventSourcingHandler
    private fun on(event: OrderCreatedEvent) {
        logger.debug("event handle $event")

        this.id = event.id
        this.state = OrderState.PENDING
        this.quantity = event.quantity
    }

    @CommandHandler
    fun approvePayment(command: ApprovePaymentOrderCommand) {
        logger.debug("command handle $command")
        apply(OrderPaidEvent(command.id))
    }

    @EventSourcingHandler
    private fun on(event: OrderPaidEvent) {
        logger.debug("event handle $event")
        this.state = OrderState.PAID
        // TODO Item 재고 변경 이벤트 발행
    }

    @CommandHandler
    fun cancel(command: CancelOrderCommand) {
        logger.debug("command handle $command")
        apply(OrderCanceledEvent(command.id))
    }

    @EventSourcingHandler
    private fun on(event: OrderCanceledEvent) {
        logger.debug("event handle $event")
        this.state = OrderState.CANCELED
        // TODO Item 재고 변경 이벤트 발행
    }
}
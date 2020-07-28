package com.example.axontutorial.infra

import com.example.axontutorial.domain.OrderState
import com.example.axontutorial.infra.repository.OrderEntity
import com.example.axontutorial.infra.repository.OrderEntityRepository
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class OrderProjection(private val orderEntityRepository: OrderEntityRepository) {

    @EventHandler
    private fun on(event: OrderCreatedEvent) {
        val entity = OrderEntity(id = event.id, state = OrderState.PENDING, quantity = event.quantity)
        orderEntityRepository.save(entity)
    }

    @EventHandler
    private fun on(event: OrderPaidEvent) {
        orderEntityRepository.findById(event.id).ifPresent {
            it.pay()
        }
    }

    @EventHandler
    private fun on(event: OrderCanceledEvent) {
        orderEntityRepository.findById(event.id).ifPresent {
            it.cancel()
        }
    }

    @QueryHandler
    fun handle(query: FetchAllOrdersQuery): List<OrderDto> {
        return orderEntityRepository.findAll()
            .map { OrderDto(id = it.id, state = it.state, quantity = it.quantity) }
    }

    @QueryHandler
    fun handle(query: FetchOrderByIdQuery): OrderDto {
        return orderEntityRepository.findById(query.id)
            .map { OrderDto(id = it.id, state = it.state, quantity = it.quantity) }
            .orElseThrow()
    }

}
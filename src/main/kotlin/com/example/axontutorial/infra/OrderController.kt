package com.example.axontutorial.infra

import com.example.axontutorial.domain.Order
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.Future

@RestController
@RequestMapping("/order")
class OrderController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    @PostMapping
    fun insert(@RequestBody dto: CreateOrderDto): ResponseEntity<Unit> {
        val command = CreateOrderCommand(id = dto.id, quantity = dto.quantity)
        commandGateway.send<CreateOrderCommand>(command)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @PutMapping("/approval/{id}")
    fun approve(@PathVariable id: String): ResponseEntity<Unit> {
        val command = ApprovePaymentOrderCommand(id)
        commandGateway.send<CreateOrderCommand>(command)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @PutMapping("/cancel/{id}")
    fun cancel(@PathVariable id: String): ResponseEntity<Unit> {
        val command = CancelOrderCommand(id)
        commandGateway.send<CreateOrderCommand>(command)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @GetMapping
    fun fetchAll(): List<OrderDto> {
        val future = queryGateway.query(FetchAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderDto::class.java))
        return future.get()
    }

    @GetMapping("/{id}")
    fun fetchById(@PathVariable id: String): OrderDto {
        return queryGateway.query(FetchOrderByIdQuery(id), ResponseTypes.instanceOf(OrderDto::class.java)).get()
    }
}
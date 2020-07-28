package com.example.axontutorial.infra.repository

import org.springframework.data.repository.CrudRepository

interface OrderEntityRepository : CrudRepository<OrderEntity, String>
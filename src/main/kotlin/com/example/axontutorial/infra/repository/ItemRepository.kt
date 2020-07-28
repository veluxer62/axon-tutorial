package com.example.axontutorial.infra.repository

import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<ItemEntity, String>
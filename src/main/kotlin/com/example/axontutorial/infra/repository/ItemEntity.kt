package com.example.axontutorial.infra.repository

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "item")
data class ItemEntity(
    @Id
    val id: String,
    val name: String,
    val stock: Int
)
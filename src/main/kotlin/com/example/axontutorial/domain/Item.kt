package com.example.axontutorial.domain

class Item(
    private val id: String,
    private val name: String,
    private var stock: Int
) {
    fun getId() = id
    fun getName() = name
    fun getStock() = stock
    fun setStock(stock: Int) {
        if (this.stock < stock) {
            throw IllegalArgumentException("재고가 부족합니다.")
        }

        this.stock = stock
    }
}
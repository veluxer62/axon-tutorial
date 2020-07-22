package com.example.axontutorial.repository

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class BookEntity(
    @Id
    val isbn: String,
    var libraryId: Int,
    var title: String
)
package com.example.axontutorial.repository

import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookEntity, String> {
    fun findByLibraryId(libraryId: Int): List<BookEntity>
}
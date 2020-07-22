package com.example.axontutorial.repository

import com.example.axontutorial.events.BookCreatedEvent
import com.example.axontutorial.models.BookBean
import com.example.axontutorial.queries.GetBookQuery
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class BookRepositoryProjector(private val bookRepository: BookRepository) {

    @EventHandler
    fun addBook(event: BookCreatedEvent) {
        val entity = BookEntity(
            isbn = event.isbn,
            libraryId = event.libraryId,
            title = event.title
        )
        bookRepository.save(entity)
    }

    @QueryHandler
    fun getBooks(query: GetBookQuery): List<BookBean> {
        return bookRepository.findByLibraryId(query.libraryId)
            .map {
                BookBean(
                    isbn = it.isbn,
                    title = it.title
                )
            }
    }

}
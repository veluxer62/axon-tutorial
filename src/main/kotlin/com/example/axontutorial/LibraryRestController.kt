package com.example.axontutorial

import com.example.axontutorial.aggregate.Library
import com.example.axontutorial.command.RegisterBookCommand
import com.example.axontutorial.command.RegisterLibraryCommand
import com.example.axontutorial.models.BookBean
import com.example.axontutorial.models.LibraryBean
import com.example.axontutorial.queries.GetBookQuery
import com.example.axontutorial.queries.GetLibraryQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/library")
class LibraryRestController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @PostMapping
    fun addLibrary(@RequestBody library: LibraryBean): ResponseEntity<Unit> {
        val command = RegisterLibraryCommand(libraryId = library.libraryId, name = library.name)
        commandGateway.send<RegisterLibraryCommand>(command)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{library}")
    fun getLibrary(@PathVariable library: Int): Library {
        val future = queryGateway.query(GetLibraryQuery(libraryId = library), Library::class.java)
        return future.get()
    }

    @PostMapping("/{library}/book")
    fun addBook(@PathVariable library: Int, @RequestBody book: BookBean): ResponseEntity<Unit> {
        val command = RegisterBookCommand(libraryId = library, isbn = book.isbn, title = book.title)
        commandGateway.send<RegisterBookCommand>(command)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{library}/book")
    fun getBook(@PathVariable library: Int): List<BookBean> {
        val future = queryGateway.query(
            GetBookQuery(libraryId = library),
            ResponseTypes.multipleInstancesOf(BookBean::class.java)
        )
        return future.get()
    }

}
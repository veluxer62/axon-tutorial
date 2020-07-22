package com.example.axontutorial.aggregate

import com.example.axontutorial.command.RegisterLibraryCommand
import com.example.axontutorial.events.BookCreatedEvent
import com.example.axontutorial.events.LibraryCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import kotlin.properties.Delegates

@Aggregate
class Library protected constructor() {

    @AggregateIdentifier
    private var libraryId = 0
        get() = field
    private var name = ""
        get() = field
    private var isbnBooks = mutableListOf<String>()
        get() = field

    @CommandHandler
    constructor(cmd: RegisterLibraryCommand): this() {
        AggregateLifecycle.apply(LibraryCreatedEvent(cmd.libraryId, cmd.name))
    }

    @EventSourcingHandler
    private fun handleCreatedEvent(event: LibraryCreatedEvent) {
        libraryId = event.libraryId
        name = event.name
    }

    @EventSourcingHandler
    private fun addBook(event: BookCreatedEvent) {
        isbnBooks.add(event.isbn)
    }
}
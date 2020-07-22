package com.example.axontutorial.repository

import com.example.axontutorial.aggregate.Library
import com.example.axontutorial.queries.GetLibraryQuery
import org.axonframework.modelling.command.Repository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class LibraryProjector(private val libraryRepository: Repository<Library>) {

    @QueryHandler
    fun getLibrary(query: GetLibraryQuery): Library {
        val future = CompletableFuture<Library>()
        libraryRepository.load(query.libraryId.toString()).execute {
            future.complete(it)
        }
        return future.get()
    }

}
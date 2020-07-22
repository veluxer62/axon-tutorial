package com.example.axontutorial.events

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class BookCreatedEvent(
    @TargetAggregateIdentifier
    val libraryId: Int,
    val isbn: String,
    val title: String
)
package com.example.axontutorial.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterBookCommand (
    @TargetAggregateIdentifier
    val libraryId: Int,
    val isbn: String,
    val title: String
)
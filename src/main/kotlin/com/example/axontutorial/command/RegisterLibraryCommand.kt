package com.example.axontutorial.command

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterLibraryCommand(
    @TargetAggregateIdentifier
    val libraryId: Int,
    val name: String
)
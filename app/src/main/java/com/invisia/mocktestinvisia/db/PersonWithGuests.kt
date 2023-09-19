package com.invisia.mocktestinvisia.db

import androidx.room.Embedded
import androidx.room.Relation

data class PersonWithGuests(
    @Embedded val person: PersonEntity, // Embeds a PersonEntity object into this class
    @Relation(
        parentColumn = "id", // Specifies the parent entity's column to match (PersonEntity's 'id')
        entityColumn = "personId" // Specifies the related entity's column to match (GuestEntity's 'personId')
    )
    val guests: List<GuestEntity> // Stores a list of GuestEntity objects related to the person
)






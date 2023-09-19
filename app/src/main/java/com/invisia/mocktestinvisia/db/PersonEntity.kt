package com.invisia.mocktestinvisia.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people") // Specifies that this class represents a database entity named "people"
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) // Marks the 'id' field as the primary key with auto-generation
    val id: Long = 0, // Default value is 0, used for auto-generation
    val name: String, // Stores the name of the person
    val type: String // Stores the type of the person
)


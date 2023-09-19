package com.invisia.mocktestinvisia.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define an Entity class for the "children" table in the database.
@Entity(tableName = "children")
data class ChildEntity(
    // Specify the primary key and auto-generation of IDs.
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    // Represent the foreign key relationship with the parent guest using Long.
    val guestId: Long,
    // Store the age of the child.
    val age: Int
)




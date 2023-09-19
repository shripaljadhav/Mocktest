package com.invisia.mocktestinvisia.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guests")
data class GuestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // Unique identifier for the guest entity
    val personId: Long, // Foreign key referencing the person this guest belongs to
    val adults: Int, // Number of adult guests
    val children: List<Int> // List of ages of children in this guest entity
)








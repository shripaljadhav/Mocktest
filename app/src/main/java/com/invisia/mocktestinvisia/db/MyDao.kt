package com.invisia.mocktestinvisia.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
@Dao
interface MyDao {
    @Insert
    suspend fun insertPerson(person: PersonEntity): Long
    // Inserts a person entity into the database and returns the generated ID

    @Insert
    suspend fun insertGuest(guest: GuestEntity): Long
    // Inserts a guest entity into the database and returns the generated ID

    @Insert
    suspend fun insertChildren(children: List<ChildEntity>)
    // Inserts a list of children entities into the database

    @Transaction
    @Query("SELECT * FROM people")
    fun getAllPeopleWithGuests(): LiveData<List<PersonWithGuests>>
    // Retrieves a list of people with associated guest entities and wraps it in LiveData

    // Other database operations
}


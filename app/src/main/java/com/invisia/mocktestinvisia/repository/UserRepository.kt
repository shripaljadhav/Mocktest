package com.invisia.mocktestinvisia.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.invisia.mocktestinvisia.data.ApiService
import com.invisia.mocktestinvisia.db.*

class UserRepository(private val apiService: ApiService, private val myDao: MyDao) {

    // Function to fetch data from the API and save it to the local database
    suspend fun fetchDataAndSaveToDatabase() {
        try {
            // Make a network request to get people data
            val response = apiService.getPeopleData()

            if (response.isSuccessful) {
                // Extract the 'dummy' data from the response
                val data = response.body()?.dummy

                if (data != null) {
                    // Map each 'person' object in the 'dummy' data
                    val entities = data.map { person ->
                        // Insert a PersonEntity into the database and get its ID
                        val personId = myDao.insertPerson(
                            PersonEntity(name = person.name, type = person.type)
                        )

                        // Map each 'guest' object in the 'person'
                        val guestEntities = person.guest.map { guest ->
                            // Insert a GuestEntity into the database and get its ID
                            val guestId = myDao.insertGuest(
                                GuestEntity(
                                    personId = personId,
                                    adults = guest.adults,
                                    children = guest.children // Keep the children field
                                )
                            )

                            // Map each child age and create ChildEntity objects
                            val childEntities = guest.children.map { childAge ->
                                ChildEntity(
                                    guestId = guestId,
                                    age = childAge
                                )
                            }

                            // Insert children associated with this guest into the database
                            myDao.insertChildren(childEntities)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            // Handle network error (exception)
        }
    }

    // LiveData representing a list of people with their guests
    val allPeopleWithGuests: LiveData<List<PersonWithGuests>> =
        myDao.getAllPeopleWithGuests()

}


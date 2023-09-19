package com.invisia.mocktestinvisia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invisia.mocktestinvisia.repository.UserRepository
import com.invisia.mocktestinvisia.db.PersonWithGuests



class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // Suspend function to fetch data and save it to the database
    suspend fun fetchDataAndSave() {
        // Call the repository's method to fetch data and save it
        userRepository.fetchDataAndSaveToDatabase()
    }

    // LiveData to observe the list of people with guests
    val allPeopleWithGuests: LiveData<List<PersonWithGuests>> =
        userRepository.allPeopleWithGuests
}



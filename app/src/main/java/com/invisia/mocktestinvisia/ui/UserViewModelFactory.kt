package com.invisia.mocktestinvisia.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invisia.mocktestinvisia.data.ApiService
import com.invisia.mocktestinvisia.repository.UserRepository
import com.invisia.mocktestinvisia.db.MyDao

class UserViewModelFactory(private val myDao: MyDao, private val apiService: ApiService) :
    ViewModelProvider.Factory {

    // Factory method to create ViewModels
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel class is UserViewModel
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            // Create and return an instance of UserViewModel
            return UserViewModel(UserRepository(apiService, myDao)) as T
        }
        // If the requested class is not UserViewModel, throw an exception
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}



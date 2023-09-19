package com.invisia.mocktestinvisia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.invisia.mocktestinvisia.data.RetrofitClient
import com.invisia.mocktestinvisia.repository.UserRepository
import com.invisia.mocktestinvisia.db.MyDatabase
import com.invisia.mocktestinvisia.ui.PeopleAdapter
import com.invisia.mocktestinvisia.ui.UserViewModel
import com.invisia.mocktestinvisia.ui.UserViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the DAO, ApiService, and UserRepository
        val myDao = MyDatabase.getInstance(application).myDao()
        val apiService = RetrofitClient.apiService

        // Initialize the RecyclerView and its adapter
        recyclerView = findViewById(R.id.recyclerView)
        adapter = PeopleAdapter()
        recyclerView.adapter = adapter

        // Create an instance of UserViewModel using a ViewModelProvider and UserViewModelFactory
        viewModel = ViewModelProvider(this, UserViewModelFactory(myDao, apiService))
            .get(UserViewModel::class.java)

        // Use lifecycleScope to launch a coroutine for fetching and saving data
        lifecycleScope.launch {
            viewModel.fetchDataAndSave()
        }

        // Observe changes in the LiveData and update the RecyclerView adapter
        viewModel.allPeopleWithGuests.observe(this, { peopleWithGuests ->
            adapter.submitList(peopleWithGuests)
        })
    }
}

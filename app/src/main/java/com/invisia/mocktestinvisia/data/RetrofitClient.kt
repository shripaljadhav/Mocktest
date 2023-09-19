package com.invisia.mocktestinvisia.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Create a RetrofitClient object to manage network requests.
object RetrofitClient {
    // Define the base URL for the API endpoints.
    private const val BASE_URL = "https://run.mocky.io/"

    // Create a lazy-initialized instance of the ApiService interface using Retrofit.
    val apiService: ApiService by lazy {
        // Create a Retrofit instance with the base URL and Gson converter factory.
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Use the Retrofit instance to create an implementation of the ApiService interface.
        retrofit.create(ApiService::class.java)
    }
}



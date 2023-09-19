package com.invisia.mocktestinvisia.data

import com.invisia.mocktestinvisia.model.MockyResponseData
import com.invisia.mocktestinvisia.model.Person
import retrofit2.Response
import retrofit2.http.GET


// Define an interface for making network requests to retrieve people data.
interface ApiService {

    // Use the HTTP GET method to fetch data from a specific URL endpoint.
    // The endpoint URL is appended to the base URL provided by Retrofit.
    // The @GET annotation specifies the endpoint path.
    @GET("v3/10f30e04-f15c-4b5b-a72c-725c40d16616")

    // Define a suspend function to make the network request and receive a Response.
    // The suspend keyword indicates that this function can be called from a coroutine.
    suspend fun getPeopleData(): Response<MockyResponseData>
}



package com.example.data.remote

import com.example.data.model.InformDTO
import com.example.data.model.InformResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getRemoteNews(
        @Query("q") query: String = "random",
        @Query("apiKey") apiKey: String = "9f027ce942b84f13a2a341fda37435fd"
    ): Response<InformResponseDTO>


    @GET("everything")
    suspend fun searchRemoteNews(
        @Query ("q") query: String,
        @Query("apiKey") apiKey: String = "9f027ce942b84f13a2a341fda37435fd"
    ): Response<InformResponseDTO>
}
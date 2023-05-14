package com.example.data.model

data class InformResponseDTO(
    val articles: ArrayList<InformDTO>,
    val status: String,
    val totalResults: Int
)
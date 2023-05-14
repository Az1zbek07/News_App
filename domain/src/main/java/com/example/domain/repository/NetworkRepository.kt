package com.example.domain.repository

import com.example.domain.model.Inform
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    suspend fun getRemoteNews(): Flow<List<Inform>>
    suspend fun searchRemoteNews(text: String): Flow<List<Inform>>
}
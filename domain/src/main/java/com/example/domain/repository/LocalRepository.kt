package com.example.domain.repository

import com.example.domain.model.Inform
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveNew(inform: Inform)
    fun getLocalNews(): Flow<List<Inform>>
}
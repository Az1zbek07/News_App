package com.example.data.repository

import com.example.data.database.InformDao
import com.example.domain.model.Inform
import com.example.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: InformDao
) : LocalRepository {
    override suspend fun saveNew(inform: Inform) {
        dao.saveNew(inform)
    }

    override fun getLocalNews(): Flow<List<Inform>> {
        return dao.getLocalNews()
    }
}
package com.example.data.repository

import com.example.data.mapper.toInform
import com.example.data.remote.ApiService
import com.example.domain.model.Inform
import com.example.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkRepository {
    override suspend fun getRemoteNews(): Flow<List<Inform>> = flow {
        val list = apiService.getRemoteNews().body()
        val secondList = mutableListOf<Inform>()
        if (list != null) {
            for (i in list.articles){
                secondList.add(i.toInform())
            }
        }

        emit(secondList)
    }

    override suspend fun searchRemoteNews(text: String): Flow<List<Inform>> = flow  {
        val list = apiService.searchRemoteNews(text).body()
        val secondList = mutableListOf<Inform>()
        if (list != null){
            for (i in list.articles){
                secondList.add(i.toInform())
            }
        }

        emit(secondList)
    }
}
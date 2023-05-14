package com.example.domain.use_case.remote

import com.example.domain.model.Inform
import com.example.domain.repository.NetworkRepository
import com.example.domain.use_case.all.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias SearchRemoteNewsBaseUseCase = BaseUseCase<String, Flow<List<Inform>>>

class SearchRemoteNewsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
): SearchRemoteNewsBaseUseCase {
    override suspend fun invoke(parameter: String): Flow<List<Inform>> {
        return networkRepository.searchRemoteNews(parameter)
    }
}
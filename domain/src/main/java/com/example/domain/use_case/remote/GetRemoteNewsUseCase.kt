package com.example.domain.use_case.remote

import com.example.domain.model.Inform
import com.example.domain.repository.NetworkRepository
import com.example.domain.use_case.all.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetRemoteNewsBaseUseCase = BaseUseCase<Unit, Flow<List<Inform>>>

class GetRemoteNewsUseCase @Inject constructor(
    private val repository: NetworkRepository
): GetRemoteNewsBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<List<Inform>> {
        return repository.getRemoteNews()
    }
}
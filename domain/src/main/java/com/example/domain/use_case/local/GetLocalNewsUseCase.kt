package com.example.domain.use_case.local

import com.example.domain.model.Inform
import com.example.domain.repository.LocalRepository
import com.example.domain.use_case.all.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetLocalNewsBaseUseCase = BaseUseCase<Unit, Flow<List<Inform>>>

class GetLocalNewsUseCase @Inject constructor(
    private val repository: LocalRepository
): GetLocalNewsBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<List<Inform>> {
        return repository.getLocalNews()
    }
}
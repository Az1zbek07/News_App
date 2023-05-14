package com.example.domain.use_case.local

import com.example.domain.model.Inform
import com.example.domain.repository.LocalRepository
import com.example.domain.use_case.all.BaseUseCase
import javax.inject.Inject

typealias SaveNewBaseUseCase = BaseUseCase<Inform, Unit>

class SaveNewUseCase @Inject constructor(
    private val repository: LocalRepository
): SaveNewBaseUseCase {
    override suspend fun invoke(parameter: Inform) {
        repository.saveNew(parameter)
    }
}
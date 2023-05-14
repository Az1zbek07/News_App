package com.example.domain.use_case.all

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(parameter: Parameter): Result
}
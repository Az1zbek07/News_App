package com.example.domain.use_case.all

import com.example.domain.use_case.local.GetLocalNewsUseCase
import com.example.domain.use_case.local.SaveNewUseCase
import com.example.domain.use_case.remote.GetRemoteNewsUseCase
import com.example.domain.use_case.remote.SearchRemoteNewsUseCase

data class AllUseCase(
    val getRemoteNewsUseCase: GetRemoteNewsUseCase,
    val searchRemoteNewsUseCase: SearchRemoteNewsUseCase,
    val saveNewUseCase: SaveNewUseCase,
    val getLocalNewsUseCase: GetLocalNewsUseCase
)

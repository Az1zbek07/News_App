package com.example.newsapp.presentation.fragment.home

import com.example.domain.model.Inform

sealed class HomeState {
    object Empty: HomeState()
    object Loading: HomeState()
    data class Error(val message: String): HomeState()
    data class Success(val informList: List<Inform>): HomeState()
}
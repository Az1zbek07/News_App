package com.example.newsapp.presentation.fragment.home

sealed class HomeEvent {
    data class OnSearched(val text: String): HomeEvent()
    object OnRandom: HomeEvent()
    object OnSports: HomeEvent()
    object OnGaming: HomeEvent()
}
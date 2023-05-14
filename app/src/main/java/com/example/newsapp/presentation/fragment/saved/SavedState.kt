package com.example.newsapp.presentation.fragment.saved

import com.example.domain.model.Inform

sealed class SavedState {
    object Empty: SavedState()
    object Loading: SavedState()
    data class Error(val message: String): SavedState()
    data class Success(val informList: List<Inform>): SavedState()
}
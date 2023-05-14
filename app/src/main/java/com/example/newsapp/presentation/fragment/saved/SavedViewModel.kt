package com.example.newsapp.presentation.fragment.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.all.AllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val allUseCase: AllUseCase
): ViewModel() {
    private val _state: MutableStateFlow<SavedState> = MutableStateFlow(SavedState.Empty)
    val state = _state.asStateFlow()

    fun getLocalNews(){
        viewModelScope.launch {
            allUseCase.getLocalNewsUseCase(Unit).onStart {
                _state.update {
                    SavedState.Loading
                }
            }.catch {tho ->
                _state.update {
                    SavedState.Error(tho.message.toString())
                }
            }.collect(){list ->
                _state.update {
                    SavedState.Success(list)
                }
            }
        }
    }
}
package com.example.newsapp.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Inform
import com.example.domain.use_case.all.AllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allUseCase: AllUseCase
): ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Empty)
    val state = _state.asStateFlow()


    fun getRemoteNews(){
        viewModelScope.launch {
            allUseCase.getRemoteNewsUseCase(Unit).onStart {
                _state.update {
                    HomeState.Loading
                }
            }.catch {thro ->
                _state.update {
                    HomeState.Error(thro.message.toString())
                }
            }.collect(){list ->
                _state.update {
                    HomeState.Success(list)
                }
            }
        }
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnSearched -> {
                searchApi(event.text)
            }

            is HomeEvent.OnGaming -> {
                searchApi("gaming")
            }

            is HomeEvent.OnRandom -> {
                searchApi("random")
            }

            is HomeEvent.OnSports -> {
                searchApi("sport")
            }
        }
    }

    private fun searchApi(text: String){
        viewModelScope.launch {
            allUseCase.searchRemoteNewsUseCase(text).onStart {
                _state.update {
                    HomeState.Loading
                }
            }.catch {thro ->
                _state.update {
                    HomeState.Error(thro.message.toString())
                }
            }.collect(){list ->
                _state.update {
                    HomeState.Success(list)
                }
            }
        }
    }

    fun saveNew(inform: Inform){
        viewModelScope.launch {
            allUseCase.saveNewUseCase(inform)
        }
    }
}
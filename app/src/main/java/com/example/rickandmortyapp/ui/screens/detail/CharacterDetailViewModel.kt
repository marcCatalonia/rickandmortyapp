package com.example.rickandmortyapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.error.DomainError
import com.example.rickandmortyapp.domain.result.DataResult
import com.example.rickandmortyapp.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val id: Int = checkNotNull(savedStateHandle["characterId"])


    private val _uiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    init { load() }

    fun onRetry() = load()


    private fun load(){
        _uiState.value = CharacterDetailUiState.Loading
        viewModelScope.launch {
            val result = getCharacterByIdUseCase(id)
            _uiState.value = when (result) {
                is DataResult.Success -> CharacterDetailUiState.Success(result.data)
                is DataResult.Failure -> when (result.error) {
                    DomainError.NotFound -> CharacterDetailUiState.NotFound
                    else -> CharacterDetailUiState.Error(result.error)
                }
            }
        }
    }
}
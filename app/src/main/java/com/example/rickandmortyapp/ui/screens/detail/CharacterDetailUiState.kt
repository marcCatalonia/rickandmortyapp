package com.example.rickandmortyapp.ui.screens.detail

import com.example.rickandmortyapp.domain.error.DomainError
import com.example.rickandmortyapp.domain.model.Character

sealed interface CharacterDetailUiState{
    data object Loading: CharacterDetailUiState
    data class Success(val character: Character): CharacterDetailUiState
    data object NotFound: CharacterDetailUiState
    data class Error(val error: DomainError): CharacterDetailUiState
}
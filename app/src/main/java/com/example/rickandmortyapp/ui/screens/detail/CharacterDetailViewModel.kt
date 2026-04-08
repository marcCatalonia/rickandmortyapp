package com.example.rickandmortyapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val id: Int = checkNotNull(savedStateHandle["characterId"])
    fun getCharacterById() = charactersRepository.getCachedCharacters(id)
}
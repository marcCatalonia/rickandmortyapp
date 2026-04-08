package com.example.rickandmortyapp.ui.screens.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortyapp.data.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    //private val api: RickyMortyAPI
    private val repository: CharactersRepository
): ViewModel(){

   /* private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState = _uiState.asStateFlow()*/

    val characters = repository.getCharactersByPage().cachedIn(viewModelScope)

    /*init {
        loadFirstCharacter()
    }
    private fun loadFirstCharacter(){
        viewModelScope.launch {
            _uiState.value.isLoading = true
            val characters = repository.getCharactersByPage()
            _uiState.value.isLoading = false
            Log.d("API", "loadFirstCharacter: $characters")
        }
    }*/
}
package com.example.rickandmortyapp.data.repository

import androidx.paging.PagingData
import com.example.rickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository{

    fun getCharactersByPage(): Flow<PagingData<Character>>

    fun getCachedCharacters(id:Int): Character?
}
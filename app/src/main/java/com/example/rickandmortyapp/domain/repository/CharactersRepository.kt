package com.example.rickandmortyapp.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository{

    fun getCharactersByPage(): Flow<PagingData<Character>>

    fun getCachedCharacters(id:Int): Character?

    suspend fun getCharacterById(id: Int): Character?
}
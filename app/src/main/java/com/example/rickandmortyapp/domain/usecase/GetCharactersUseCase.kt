package com.example.rickandmortyapp.domain.usecase

import androidx.paging.PagingData
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<PagingData<Character>> = repository.getCharactersByPage()
}
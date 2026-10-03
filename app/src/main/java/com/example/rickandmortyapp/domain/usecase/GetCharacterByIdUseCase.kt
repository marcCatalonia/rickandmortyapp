package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(id: Int) = repository.getCharacterById(id)
}
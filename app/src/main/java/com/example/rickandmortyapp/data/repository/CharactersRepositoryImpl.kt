package com.example.rickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.data.mapper.toDomain
import com.example.rickandmortyapp.data.paging.CharactersPagingSource
import com.example.rickandmortyapp.data.remote.RickyMortyAPI
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val api: RickyMortyAPI
): CharactersRepository {
    private val cache = mutableMapOf<Int, Character>()

    override fun getCharactersByPage(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(api) { characters ->
                    characters.forEach {
                        cache[it.id] = it
                    }

                }
            }
        ).flow
    }

    override fun getCachedCharacters(id:Int): Character?{
        return cache[id]
    }

    override suspend fun getCharacterById(id: Int): Character? {
        cache[id]?.let { return it }

        return try {
            val character = api.getCharacterById(id).toDomain()
            cache[id] = character
            character
        }catch (e: Exception){
            null
        }

    }
}
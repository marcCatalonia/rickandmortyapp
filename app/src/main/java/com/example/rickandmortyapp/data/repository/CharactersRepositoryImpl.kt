package com.example.rickandmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.data.paging.CharactersPagingSource
import com.example.rickandmortyapp.data.remote.RickyMortyAPI
import com.example.rickandmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val api: RickyMortyAPI
): CharactersRepository {
    private val cache = mutableMapOf<Int, com.example.rickandmortyapp.domain.model.Character>()

    override fun getCharactersByPage(): Flow<PagingData<com.example.rickandmortyapp.domain.model.Character>> {
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
}
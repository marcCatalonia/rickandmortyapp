package com.example.rickandmortyapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.data.mapper.toDomain
import com.example.rickandmortyapp.data.remote.RickyMortyAPI

class CharactersPagingSource(
    private val api: RickyMortyAPI,
    private val onLoaded: (List<Character>) -> Unit
): PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1

            val response = api.getCharacters(page)

            val characters = response.results.map { it.toDomain() }

            onLoaded(characters)

            Log.d("CharactersPagingSource", "load: $response")
            Log.d("CharactersPagingSource", "load characters: $characters")
            LoadResult.Page(
                data = characters,
                prevKey = if(page == 1) null else page - 1,
                nextKey = if(response.info.next == null) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

}
package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickyMortyAPI {

    @GET("character")
    suspend fun getCharacters(
        @Query("page")page: Int
    ): CharacterResponseDto
}
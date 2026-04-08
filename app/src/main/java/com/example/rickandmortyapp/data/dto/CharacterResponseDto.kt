package com.example.rickandmortyapp.data.dto

data class CharacterResponseDto(
    val info: InfoDto,
    val results : List<CharacterDto>
)

package com.example.rickandmortyapp.data.dto

data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginForCharacter,
    val location: LocationForCharacter,
    val image: String,
    val episodes: List<String>,
    val url: String,
    val created: String
)

data class OriginForCharacter(
    val name: String,
    val url: String
)

data class LocationForCharacter(
    val name: String,
    val url: String
)
